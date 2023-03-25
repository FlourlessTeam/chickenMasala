package com.example.chickenmasala.interactors

class GetCuisineImageAndName(private val dataSource: RecipesDataSource) {
    fun execute(): ArrayList<Pair<String, String>> {
        val cuisineName = dataSource.getAllRecipesData().groupBy { it.cuisine }
        val cuisineNameAndImage = arrayListOf<Pair<String, String>>()
        cuisineName.forEach {
            cuisineNameAndImage.add(Pair(it.key, it.value[0].imageUrl))
        }
        return cuisineNameAndImage
    }
}