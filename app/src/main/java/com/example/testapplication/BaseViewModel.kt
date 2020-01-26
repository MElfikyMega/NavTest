package com.example.testapplication

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    init {
        Log.d(javaClass.simpleName, "init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(javaClass.simpleName, "onCleared")
    }

}