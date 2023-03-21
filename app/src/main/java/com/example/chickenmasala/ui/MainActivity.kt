package com.example.chickenmasala.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chickenmasala.R
import com.example.chickenmasala.data.entities.Recipe
import com.example.chickenmasala.util.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFile()
    }

    private fun openFile() {
        val inputStream = assets.open("indian_food_v3.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        val list = parser.convertToListOfMappedStrings(buffer)
        Log.d("main_activity", list.last().translatedInstructions)
    }
}