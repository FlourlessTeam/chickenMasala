package com.example.chickenmasala.interactors

class GetSubcategories(private val dataSource: RecipesDataSource) {
    fun execute(): ArrayList<Triple<String, String, String>> {
        val result = arrayListOf<Triple<String, String, String>>()
        dataSource.allRecipesData.take(100).forEach {
            result.add(Triple(it.translatedRecipeName, it.totalTimeInMins.toString(), it.imageUrl))
        }
        return result
    }
}