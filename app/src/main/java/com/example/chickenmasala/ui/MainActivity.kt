package com.example.chickenmasala.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.data.util.CsvParser
import com.example.chickenmasala.interactors.GetAllCuisines
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    val dataManager by lazy { DataManager(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val arr = GetAllCuisines(dataManager).execute().keys.toList()
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment,CuisinesFragment.newInstance(arrayListOf(*arr.toTypedArray())))
            .commit()


    }





}