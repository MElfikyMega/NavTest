package com.example.testapplication

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.testapplication.databinding.Fragment1Binding

const val isLoggedInKey = "isLoggedIn"
const val isLoggedInDefault = false

class Fragment1 : BaseFragment(R.layout.fragment_1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = Fragment1Binding.bind(view)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isLoggedIn = sharedPreferences.getBoolean(isLoggedInKey, isLoggedInDefault)

        if (!isLoggedIn) {
            findNavController().navigate(Fragment1Directions.actionGlobalAuthNav())
        } else {
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
}