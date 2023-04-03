package com.example.chickenmasala.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.ActivityMainBinding
import com.example.chickenmasala.ui.fragments.*
import com.example.chickenmasala.ui.fragments.navigationbarfragments.FavouriteResultFragment
import com.example.chickenmasala.ui.fragments.navigationbarfragments.HomeFragment
import com.example.chickenmasala.ui.fragments.navigationbarfragments.SearchFragment
import com.example.chickenmasala.ui.fragments.navigationbarfragments.SettingFragment
import com.example.chickenmasala.ui.interfaces.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    companion object {
        private var isFirstTimeHomeTransition = true
        private var currentFragmentId = R.id.navigation_home
    }

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        setupBottomNavBar()
        setContentView(binding.root)
    }

    private fun setupBottomNavBar() {
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener {
            if (isSelectedItemIsCurrentItem(it, bottomNavigationView))
                return@setOnItemSelectedListener false
            else {
                clearPreviousFragmentStack()
                val fragment = getFragmentFromSelectedItem(it)
                navigateToSelectedFragment(fragment)
            }

            return@setOnItemSelectedListener true
        }
        resumeBottomNavBarState()

    }

    private fun navigateToSelectedFragment(fragment: BaseFragment<out ViewBinding>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun isSelectedItemIsCurrentItem(
        it: MenuItem,
        bottomNavigationView: BottomNavigationView
    ) = it.itemId == bottomNavigationView.selectedItemId && !isFirstTimeHomeTransition

    private fun getFragmentFromSelectedItem(item: MenuItem) = when (item.itemId) {
        R.id.navigation_home -> {
            isFirstTimeHomeTransition = false
            currentFragmentId = item.itemId; HomeFragment()
        }

        R.id.navigation_search -> {
            currentFragmentId = item.itemId; SearchFragment()
        }

        R.id.navigation_favourite -> {
            currentFragmentId = item.itemId; FavouriteResultFragment()
        }

        R.id.navigation_settings -> {
            currentFragmentId = item.itemId; SettingFragment()
        }

        else -> SettingFragment()
    }

    private fun clearPreviousFragmentStack() {
        while (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    private fun resumeBottomNavBarState() {
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = currentFragmentId
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStackImmediate()
            } else {
                if (!isHomeCurrentBaseFragment()) {
                    setHomeAsBaseFragment()
                    return
                }
                isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }

        }
    }

    private fun isHomeCurrentBaseFragment() =
        currentFragmentId == R.id.navigation_home

    private fun setHomeAsBaseFragment() {
        currentFragmentId = R.id.navigation_home
        resumeBottomNavBarState()
    }


}