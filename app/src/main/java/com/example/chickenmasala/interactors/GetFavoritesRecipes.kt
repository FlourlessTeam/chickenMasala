package com.example.chickenmasala.interactors

class GetFavoritesRecipes(private val dataSource: RecipesDataSource) {
    fun execute() = dataSource.allRecipesData.filter { !it.isFavourite }


}