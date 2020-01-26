package com.example.testapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.splash.*

const val isFirstTimeKey = "isFirstTime"
const val isFirstTimeDefault = true

class DecisionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        createNotificationChannel()
        showNotification()

        // val test = NavController(this)
        // test.setGraph(R.navigation.decision_nav)
        // test.navigate()

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
                    // test.navigate(R.id.onBoardingActivity)
                }
            })
        } else {
            MainActivity.start(this)
            finish()
            // test.navigate(R.id.mainActivity)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test Channel"
            val descriptionText = "Description Text"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("Channel Id", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification() {
        val pendingIntent = NavDeepLinkBuilder(this)
            .setGraph(R.navigation.main_nav)
            .setDestination(R.id.activity1)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(this, "Channel Id")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Test Notification")
            .setContentText("Content Text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_notification, "Action 1", pendingIntent)
            .addAction(
                R.drawable.ic_notification,
                "Action 2",
                PendingIntent.getActivity(this, 1, Intent(this, DecisionActivity::class.java), 0)
            )
            .addAction(
                R.drawable.ic_notification,
                "Action 3",
                PendingIntent.getActivity(this, 1, Intent(this, DecisionActivity::class.java), 0)
            )
            .setOngoing(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1000, builder.build())
        }
    }
}