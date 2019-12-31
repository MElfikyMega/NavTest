package com.example.testapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.testapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val topLevelDestinations =
        setOf(R.id.fragment1, R.id.fragment2, R.id.fragment3, R.id.fragment4)

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
                if (topLevelDestinations.contains(destination.id)) {
                    rootDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    fab.visibility = View.VISIBLE
                    bottomNav.visibility = View.VISIBLE
                } else {
                    rootDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    fab.visibility = View.GONE
                    bottomNav.visibility = View.GONE
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