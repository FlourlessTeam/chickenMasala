package com.example.chickenmasala.interactors

class GetRecipesLessThanGivenTime(private val dataSource: RecipesDataSource) {
    fun execute(maxTime: Int, limit: Int) =
        dataSource.getAllRecipesData().filter { it.totalTimeInMins <= maxTime }.take(limit)
}