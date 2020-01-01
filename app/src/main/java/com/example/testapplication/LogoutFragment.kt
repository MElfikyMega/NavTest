package com.example.testapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.testapplication.databinding.LogoutFragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogoutFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LogoutFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        lifecycleScope.launch {
            delay(2000)

            sharedPreferences.edit {
                putBoolean(isLoggedInKey, false)
            }

            findNavController().navigate(LogoutFragmentDirections.actionGlobalAuthNav())
        }
    }
}