package com.example.chickenmasala.interactors

class GetAllCuisines(private val dataSource: RecipesDataSource) {
    fun execute() =
        dataSource.getAllRecipesData().groupBy { it.cuisine }

}