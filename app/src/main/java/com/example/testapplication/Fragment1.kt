package com.example.testapplication

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.testapplication.databinding.Fragment1Binding

class Fragment1 : BaseFragment(R.layout.fragment_1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Log.d(javaClass.simpleName, "onViewCreated")

        val binding = Fragment1Binding.bind(view)

        with(binding) {
            action1Tv.setOnClickListener {
                // findNavController().navigate(Fragment1Directions.actionFragment1ToFragment2())
            }

            action2Tv.setOnClickListener {
                findNavController().navigate(Fragment1Directions.actionFragment1ToActivity1())
            }
        }
    }
}