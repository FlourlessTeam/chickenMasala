package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Recipe

class GetFavoritesRecipes(private val dataSource: RecipesDataSource) {
    fun execute(): List<Recipe> {
        return dataSource.getAllRecipesData().filter { it.isFavourite }
    }

}