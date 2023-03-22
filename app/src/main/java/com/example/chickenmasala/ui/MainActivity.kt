package com.example.chickenmasala.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager

class MainActivity : AppCompatActivity() {
    val dataManager by lazy { DataManager(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}