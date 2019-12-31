package com.example.testapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(javaClass.simpleName, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(javaClass.simpleName, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(javaClass.simpleName, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(javaClass.simpleName, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(javaClass.simpleName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(javaClass.simpleName, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(javaClass.simpleName, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(javaClass.simpleName, "onDestroy")
    }

}