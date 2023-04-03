package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Recipe

class SearchRecipes(private val dataSource: RecipesDataSource) {
    fun executeAdvancedSearchBasedOnQuery(
        name: String,
        cookingTime: Int,
        ingredientCount: Int,
    ): List<Recipe> {
        return dataSource.getAllRecipesData().filter {
            if (name == "") {
                it.totalTimeInMins <= cookingTime &&
                        it.ingredientCount <= ingredientCount
            } else {
                it.translatedRecipeName.contains(name) &&
                        it.totalTimeInMins <= cookingTime &&
                        it.ingredientCount <= ingredientCount
            }
        }.take(20)
    }
}