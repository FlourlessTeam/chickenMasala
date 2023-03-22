package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Recipe

class SearchRecipes(private val dataSource: RecipesDataSource) {
    fun execute(string: String): List<Recipe> {
       return dataSource.allRecipesData.filter {
            it.cuisine.contains(string) || it.translatedRecipeName.contains(string)
        }

    }
    fun limitExecute(name:String): List<Recipe>{
        return dataSource.allRecipesData.filter { it.translatedRecipeName.contains(name) }.shuffled().take(20)
    }
}