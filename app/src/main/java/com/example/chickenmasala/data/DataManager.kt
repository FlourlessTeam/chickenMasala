package com.example.chickenmasala.data

import android.content.Context
import com.example.chickenmasala.data.util.CsvParser
import com.example.chickenmasala.interactors.RecipesDataSource
import java.io.BufferedReader
import java.io.InputStreamReader

class DataManager(private val context: Context) : RecipesDataSource {

    override val allRecipesData by lazy { CsvParser(getCsvData()).constructAllRecipes() }
    private fun getCsvData(): BufferedReader {
        val inputStream = context.assets.open("indian_food_v3.csv")
        return BufferedReader(InputStreamReader(inputStream))
    }


}