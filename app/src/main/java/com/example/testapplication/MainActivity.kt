package com.example.testapplication

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.testapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private val topLevelDestinations =
        setOf(
            R.id.fragment1,
            R.id.inboxFragment,
            R.id.fragment3,
            R.id.fragment4,
            R.id.loginFragment
        )

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        with(binding) {
            val appBarConfiguration = AppBarConfiguration(topLevelDestinations, rootDl)
            toolbar.setupWithNavController(navController, appBarConfiguration)

            navigationView.setupWithNavController(navController)

            bottomNav.setupWithNavController(navController)
            bottomNav.setOnNavigationItemReselectedListener { }

            fab.setOnClickListener {
                navController.navigate(MainNavDirections.actionGlobalFragment9())
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.parent?.id) {
                    R.id.auth_nav -> {
                        rootDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                        toolbar.visibility = View.GONE
                        fab.visibility = View.GONE
                        bottomNav.visibility = View.GONE
                    }
                    else -> {
                        toolbar.visibility = View.VISIBLE
                        when (destination.id) {
                            R.id.fragment1, R.id.inboxFragment, R.id.fragment3, R.id.fragment4 -> {
                                rootDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                                fab.visibility = View.VISIBLE
                                bottomNav.visibility = View.VISIBLE
                            }
                            else -> {
                                rootDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                                fab.visibility = View.GONE
                                bottomNav.visibility = View.GONE
                            }
                        }
                    }
                }

            }
        }
    }

    override fun onBackPressed() {
        if (binding.rootDl.isDrawerOpen(GravityCompat.START)) {
            binding.rootDl.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}