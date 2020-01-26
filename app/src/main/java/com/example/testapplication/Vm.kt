package com.example.testapplication

import androidx.lifecycle.ViewModel
import com.example.testapplication.mvi.StateStore
import com.example.testapplication.mvi.UiEvents

class Vm : ViewModel() {

    fun test(store: StateStore<Int, UiEvents>) {
//        viewModelScope.launch {
//            delay(4000)
//
//            val action = createAction<Int, UiEvents> { dispatch, getState, extraArgument ->
//                viewModelScope.launch {
//                    delay(4000)
//                    dispatch(UiEvents.Event4)
//                }
//            }
//
//            store.dispatch(action)
//        }
    }

}