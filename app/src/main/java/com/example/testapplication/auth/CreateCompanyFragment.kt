package com.example.testapplication.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.testapplication.BaseFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.CreateCompanyFragmentBinding

class CreateCompanyFragment : BaseFragment(R.layout.create_company_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CreateCompanyFragmentBinding.bind(view)

        with(binding) {
            activationBtn.setOnClickListener {
                findNavController().navigate(CreateCompanyFragmentDirections.actionCreateCompanyFragmentToActivationFragment())
            }
        }
    }

}