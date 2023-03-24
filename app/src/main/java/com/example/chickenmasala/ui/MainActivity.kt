package com.example.chickenmasala.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.ActivityMainBinding
import com.example.chickenmasala.ui.fragments.HomeFragment


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val dataManager by lazy { DataManager(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, homeFragment)
            .commit()

    }

}