package com.example.chickenmasala.interactors

class GetAllRecipes(private val dataSource: RecipesDataSource) {
    fun execute()= dataSource.allRecipesData
}