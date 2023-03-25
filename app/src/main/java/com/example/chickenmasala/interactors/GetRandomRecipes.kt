package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Recipe

class GetRandomRecipes(dataSource: RecipesDataSource) {
    private val allRecipes = dataSource.getAllRecipesData()
    fun execute(limit: Int): List<Recipe> {
        val randomListOfNumbers = (allRecipes.indices).shuffled().take(limit)
        return randomListOfNumbers.map { allRecipes[it] }
    }

}