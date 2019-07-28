package com.syntax.learn.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syntax.learn.common.Navigator
import com.syntax.learn.common.NavigatorHandler
import com.syntax.learn.screen.login.LoginFragment
import com.syntax.learn.screen.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.view.WindowManager
import android.os.Build
import com.syntax.learn.R
import com.syntax.learn.screen.register.RegisterFragment


class MainActivity : AppCompatActivity(), NavigatorHandler {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        // TODO: Ini nanti fragment nya disesuaikan yah
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(Fragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchFragment(Fragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchFragment(Fragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                switchFragment(LoginFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // di dalam onCreate
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }
    }

    override fun handleNavigation(navigator: Navigator) {
        // Untuk handle navigasi dari method navigate { }
        if (navigator is MainNavigator.Profile) {
            switchFragment(ProfileFragment())
        }
        if(navigator is MainNavigator.Register){
            supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, RegisterFragment())
                .addToBackStack("register")
                .commit()
        }
    }
}
