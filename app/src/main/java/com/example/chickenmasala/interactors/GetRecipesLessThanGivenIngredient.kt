package com.example.chickenmasala.interactors

class GetRecipesLessThanGivenIngredient(private val dataSource: RecipesDataSource) {
    fun execute(maxIngredient: Int, limit: Int) =
        dataSource.getAllRecipesData().filter { it.ingredientCount <= maxIngredient }.take(limit)


}