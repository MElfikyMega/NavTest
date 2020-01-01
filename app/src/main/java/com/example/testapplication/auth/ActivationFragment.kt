package com.example.testapplication.auth

import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.testapplication.BaseFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivationFragmentBinding
import com.example.testapplication.isLoggedInKey

class ActivationFragment : BaseFragment(R.layout.activation_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ActivationFragmentBinding.bind(view)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        with(binding) {
            loginBtn.setOnClickListener {
                sharedPreferences.edit {
                    putBoolean(isLoggedInKey, true)
                }
                findNavController().navigate(ActivationFragmentDirections.actionActivationFragmentToFragment1())
            }
        }
    }

}