package com.example.testapplication

import android.os.Bundle
import android.view.View
import com.example.testapplication.databinding.Fragment3Binding

class Fragment3 : BaseFragment(R.layout.fragment_3) {

    private lateinit var binding: Fragment3Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Fragment3Binding.bind(view)

        with(binding) {

            action1Tv.setOnClickListener {
                // findNavController().navigate(Fragment3Directions.actionFragment3ToFragment4())
            }

            action2Tv.setOnClickListener {
                //                findNavController().navigate(Fragment3Directions.actionFragment3Pop())
            }

        }
    }
}
