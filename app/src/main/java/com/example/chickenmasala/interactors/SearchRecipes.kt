package com.example.chickenmasala.interactors

class SearchRecipes(private val dataSource: RecipesDataSource) {
    fun execute(string: String) {
        dataSource.allRecipesData.filter {
            it.cuisine.contains(string) || it.translatedRecipeName.contains(string)
        }

    }
}