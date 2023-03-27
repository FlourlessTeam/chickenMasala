package com.example.chickenmasala.data

import android.content.Context
import com.example.chickenmasala.data.util.CsvParser
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.RecipesDataSource
import java.io.BufferedReader
import java.io.InputStreamReader

class DataManager(private val context: Context) : RecipesDataSource {
    companion object {
        var allRecipes: List<Recipe>? = null
    }

    private val favoritesRecipes = mutableListOf<Recipe>()

    override fun getAllRecipesData(): List<Recipe> {
        if (allRecipes == null)
            allRecipes = CsvParser(getCsvData()).constructAllRecipes()
        return allRecipes!!
    }

    private fun getCsvData(): BufferedReader {
        val inputStream = context.assets.open("indian_food_v3.csv")
        return BufferedReader(InputStreamReader(inputStream))
    }

    fun addToFavoritesRecipes(recipe: Recipe) {
        favoritesRecipes.add(recipe)
    }

    fun removeFromFavoritesRecipes(recipe: Recipe) {
        favoritesRecipes.remove(recipe)
    }


}