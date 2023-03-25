package com.example.chickenmasala.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.ActivityMainBinding
import com.example.chickenmasala.ui.fragments.BaseFragment
import com.example.chickenmasala.ui.fragments.HomeFragment
import com.example.chickenmasala.ui.fragments.SearchResultFragment
import com.example.chickenmasala.ui.fragments.SettingFragment


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavBar()
        startHomeFragmentTransaction()
    }

    private fun setupBottomNavBar() {
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.itemId == bottomNavigationView.selectedItemId) {
                return@setOnItemSelectedListener true
            }
            val fragment = when (item.itemId) {
                R.id.navigation_home -> HomeFragment()
                R.id.navigation_search -> SearchResultFragment()
                R.id.navigation_settings -> SettingFragment()
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


    }

    private fun startHomeFragmentTransaction() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val homeFragment = HomeFragment()
        fragmentTransaction.add(binding.fragmentContainer.id, homeFragment, HomeFragment.TAG)
        fragmentTransaction.commit()
    }

}