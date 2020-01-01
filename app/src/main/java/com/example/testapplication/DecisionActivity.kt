package com.example.testapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.splash.*

const val isFirstTimeKey = "isFirstTime"
const val isFirstTimeDefault = true

class DecisionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val isFirstTime = sharedPreferences.getBoolean(isFirstTimeKey, isFirstTimeDefault)

        if (isFirstTime) {
            // Show animation and onBoarding
            rootMl.transitionToEnd()

            rootMl.setTransitionListener(object : TransitionAdapter() {
                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    sharedPreferences.edit {
                        putBoolean(isFirstTimeKey, false)
                    }
                    MainActivity.start(this@DecisionActivity)
                    finish()
                }
            })

        } else {
            MainActivity.start(this)
            finish()
        }
    }
}
