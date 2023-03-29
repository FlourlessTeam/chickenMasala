package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Recipe

class SearchRecipes(private val dataSource: RecipesDataSource) {
    fun execute(string: String): List<Recipe> {
       return dataSource.getAllRecipesData().filter {
            it.cuisine.contains(string) || it.translatedRecipeName.contains(string)
        }

    }
    fun searchQuery(name:String): List<Recipe>{
        return dataSource.getAllRecipesData().filter { it.translatedRecipeName.contains(name) }.shuffled().take(20)
    }
}