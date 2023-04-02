package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Recipe

class SearchRecipes(private val dataSource: RecipesDataSource) {
    fun execute(string: String): List<Recipe> {
        return dataSource.getAllRecipesData().filter {
            it.cuisine.contains(string) || it.translatedRecipeName.contains(string)
        }

    }

    fun executeSomeSearchRecipe(name: String): List<Recipe> {
        return dataSource.getAllRecipesData().filter { it.translatedRecipeName.contains(name) }
            .shuffled().take(20)
    }

    fun executeAdvancedSearchBasedOnQuery(
        name: String,
        cookingTime: Int,
        ingredientCount: Int,
    ): List<Recipe> {
        return dataSource.getAllRecipesData().filter {
            it.translatedRecipeName.contains(name) &&
                    it.totalTimeInMins <= cookingTime &&
                    it.ingredientCount <= ingredientCount
        }.shuffled().take(20)
    }

    fun executeAdvancedSearch(cookingTime: Int, ingredientCount: Int): List<Recipe> {
        return dataSource.getAllRecipesData().filter {
            it.totalTimeInMins <= cookingTime &&
                    it.ingredientCount <= ingredientCount
        }.shuffled().take(20)
    }
}