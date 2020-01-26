package com.example.testapplication.mvi

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

sealed class UiEvents {
    object Event1 : UiEvents()
    object Event2 : UiEvents()
    object Event3 : UiEvents()
    object Event4 : UiEvents()
}

fun getThreadName(): String = Thread.currentThread().name

interface IStateStore<State, Event> {
    fun getState(): State
//    val dispatch: Dispatcher<Event>
    fun getStateFlow(): Flow<State>
}

class ActionsStore<State, Event>(
    val scope: CoroutineScope
) {

    fun onEvent(event: Event, currentState: State) {
        try {
            scope.launch {
                delay(4000)
                Log.d("onEvent ", "$event, $currentState")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

class StateStore<State, Event>(
    reducer: Reducer<State, Event>,
    initialState: State
) : IStateStore<State, Event> {

    private var currentReducer = reducer
    private var currentState = initialState
    private val stateChannel = Channel<State>(Channel.CONFLATED)
    private var isDispatching = false
    private val storeThreadName = getThreadName()
    private fun isSameThread() = getThreadName() == storeThreadName
    private fun checkSameThread() = check(isSameThread()) {
        """You may not call the store from a thread other than the thread on which it was created.
            |This includes: getState(), dispatch(), subscribe(), and replaceReducer()
            |This store was created on: '$storeThreadName' and current
            |thread is '${getThreadName()}'
            """.trimMargin()
    }

    init {
        stateChannel.offer(initialState)
    }

    override fun getState(): State {
        checkSameThread()
        check(!isDispatching) {
            """|You may not call store.getState() while the reducer is executing.
             |The reducer has already received the state as an argument.
             |Pass it down from the top reducer instead of reading it from the
             |store.""".trimMargin()
        }

        return currentState
    }

    /**
     * Dispatches an action. It is the only way to trigger a state change.
     *
     * The `reducer` function, used to create the store, will be called with the
     * current state tree and the given `action`. Its return value will
     * be considered the **next** state of the tree, and the change listeners
     * will be notified.
     *
     * The base implementation only supports plain object actions. If you want
     * to dispatch something else, such as a function or 'thunk' you need to
     * wrap your store creating function into the corresponding middleware. For
     * example, see the documentation for the `redux-thunk` package. Even the
     * middleware will eventually dispatch plain object actions using this
     * method.
     *
     * @param {Any} [action] A plain object representing “what changed”. It is
     * a good idea to keep actions serializable so you can record and replay
     * user sessions, or use the time travelling `redux-devtools`.
     *
     * @returns {Any} For convenience, the same action object you dispatched.
     *
     * Note that, if you use a custom middleware, it may wrap `dispatch()` to
     * return something else (for example, a Promise you can await).
     */
    fun dispatch(event: Event): Event {
        checkSameThread()

        check(!isDispatching) {
            "Reducers may not dispatch actions."
        }

        try {
            isDispatching = true
            currentState = currentReducer(currentState, event)
        } finally {
            isDispatching = false
        }

        stateChannel.offer(currentState)

        return event
    }

//    fun dispatch(action: Action<State, Event>) {
//        try {
//            action.dispatch(::dispatch, ::getState, null)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            throw IllegalArgumentException()
//        }
//    }

//    override val dispatch: Dispatcher<Event> = ::dispatch

    override fun getStateFlow(): Flow<State> {
        return stateChannel.consumeAsFlow()
    }

}

//typealias Dispatcher<Event> = (event: Event) -> Event
typealias Reducer<State, Event> = (state: State, event: Event) -> State

//interface Action<State, Event> {
//    fun dispatch(dispatch: Dispatcher<Event>, getState: () -> State, extraArgument: Any?): Any
//}
//
//fun <State, Event> createAction(actionLambda: (dispatch: Dispatcher<Event>, getState: () -> State, extraArgument: Any?) -> Any): Action<State, Event> {
//    return object : Action<State, Event> {
//        override fun dispatch(dispatch: Dispatcher<Event>, getState: () -> State, extraArgument: Any?): Any =
//            actionLambda(dispatch, getState, extraArgument)
//    }
//}