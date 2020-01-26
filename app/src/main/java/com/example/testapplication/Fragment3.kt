package com.example.testapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.testapplication.databinding.Fragment3Binding
import com.example.testapplication.mvi.ActionsStore
import com.example.testapplication.mvi.StateStore
import com.example.testapplication.mvi.UiEvents
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class Fragment3 : BaseFragment(R.layout.fragment_3) {

    private lateinit var binding: Fragment3Binding

    private val vm: Vm by activityViewModels()

    val stateStore = StateStore<Int, UiEvents>({ state, event ->
        when(event) {
            UiEvents.Event1 -> {
                Log.d("before sleep ", "$event")
                Thread.sleep(1000)
                Log.d("after sleep ", "$event")
                1
            }
            UiEvents.Event2 -> {
                Log.d("before sleep ", "$event")
                Thread.sleep(1000)
                Log.d("after sleep ", "$event")
                2
            }
            UiEvents.Event3 -> {
                Log.d("before sleep ", "$event")
                Thread.sleep(1000)
                Log.d("after sleep ", "$event")
                3
            }
            UiEvents.Event4 -> {
                Log.d("before sleep ", "$event")
                Thread.sleep(1000)
                Log.d("after sleep ", "$event")
                4
            }
        }
    }, 0)

    val actionStore = ActionsStore<Int, UiEvents>(lifecycleScope)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Fragment3Binding.bind(view)

        stateStore.getStateFlow()
            .onEach { state -> Log.d("flow -> ", "$state") }
            .launchIn(lifecycleScope)

        Log.d("get  -> ", "${stateStore.getState()}")

        vm.test(stateStore)

        with(binding) {

            action1Tv.setOnClickListener {
                stateStore.dispatch(UiEvents.Event1)

            }

            action2Tv.setOnClickListener {
                actionStore.onEvent(UiEvents.Event2, 2)
            }

        }


    }
}
