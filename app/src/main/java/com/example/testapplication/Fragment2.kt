package com.example.testapplication

import android.os.Bundle
import android.view.View
import com.example.testapplication.databinding.Fragment2Binding

class Fragment2 : BaseFragment(R.layout.fragment_2) {

    private lateinit var binding: Fragment2Binding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Fragment2Binding.bind(view)

        with(binding) {
            action1Tv.setOnClickListener {
                // findNavController().navigate(Fragment2Directions.actionFragment2ToFragment3())
            }
        }
    }

}
