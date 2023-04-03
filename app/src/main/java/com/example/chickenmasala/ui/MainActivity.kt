package com.example.chickenmasala.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.ActivityMainBinding
import com.example.chickenmasala.ui.fragments.*
import com.example.chickenmasala.ui.fragments.navigationbarfragments.FavouriteResultFragment
import com.example.chickenmasala.ui.fragments.navigationbarfragments.HomeFragment
import com.example.chickenmasala.ui.fragments.navigationbarfragments.SearchFragment
import com.example.chickenmasala.ui.fragments.navigationbarfragments.SettingFragment


class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
         requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        setupBottomNavBar()
        setContentView(binding.root)
    }
    private fun setupBottomNavBar() {
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.itemId == bottomNavigationView.selectedItemId && !SharedState.isFirstTimeHomeTransition ) {
                return@setOnItemSelectedListener true
            }
            val fragment = when (item.itemId) {
                R.id.navigation_home -> {
                    SharedState.isFirstTimeHomeTransition=false
                    SharedState.currentFragmentId = item.itemId; HomeFragment()
                }
                R.id.navigation_search -> {
                    SharedState.currentFragmentId = item.itemId; SearchFragment()
                }
                R.id.navigation_favourite -> {
                    SharedState.currentFragmentId = item.itemId; FavouriteResultFragment()
                }
                R.id.navigation_settings -> {
                    SharedState.currentFragmentId = item.itemId; SettingFragment()
                }
                else -> null
            }
            val isHandled = fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                true
            } ?: false
            return@setOnItemSelectedListener isHandled
        }
        resumeBottomNavBarState()

    }

    private fun resumeBottomNavBarState() {
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId =0
        bottomNavigationView.selectedItemId = SharedState.currentFragmentId
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }

        }
    }


}