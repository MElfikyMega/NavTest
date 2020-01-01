package com.example.testapplication.auth

import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.testapplication.BaseFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.LoginFragmentBinding
import com.example.testapplication.isLoggedInKey

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = LoginFragmentBinding.bind(view)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        with(binding) {
            loginBtn.setOnClickListener {
                sharedPreferences.edit {
                    putBoolean(isLoggedInKey, true)
                }
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainNav())
            }
            signUpBtn.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
            }
            createCompanyBtn.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCreateCompanyFragment())
            }
            activationBtn.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToActivationFragment())
            }
        }
    }

}