package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Cuisine

class GetRequiredHomeCuisines(private val dataSource: RecipesDataSource) {
    fun execute(limit: Int): List<Cuisine> {
        val listOfCuisines = mutableListOf<Cuisine>()
        dataSource.getAllRecipesData().groupBy { it.cuisine }.forEach { (cuisineName, recipes) ->
            listOfCuisines.add(Cuisine(cuisineName, recipes))
        }
        return listOfCuisines.take(limit)
    }
}