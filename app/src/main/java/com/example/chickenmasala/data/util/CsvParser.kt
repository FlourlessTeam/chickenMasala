package com.example.chickenmasala.data.util

import com.example.chickenmasala.entities.Recipe
import java.io.BufferedReader

class CsvParser(private val buffer: BufferedReader ) {
    fun constructAllRecipes(): List<Recipe> {
        var counter = 0
        val list = mutableListOf<String>()
        buffer.readText().split(",").forEach {
            if (counter != 0 && counter % 8 == 0) {
                list.addAll(it.lines())
            } else {
                list.add(it)
            }
            counter++
        }
        return convertMappedStringsToRecipes(list)
    }

    private fun convertMappedStringsToRecipes(mappedStrings: List<String>): List<Recipe> {
        var counter = 0
        val recipes = mutableListOf<Recipe>()
        while (counter + 8 < mappedStrings.size) {
            recipes.add(getRecipeFromStringList(mappedStrings.subList(counter, counter + 9)))
            counter += 9
        }
        return recipes
    }


    private fun getRecipeFromStringList(mappedStrings: List<String>) =
        Recipe(
            translatedRecipeName = mappedStrings[Constants.ColumnIndex.TRANSLATED_RECIPE_NAME],
            translatedIngredients = getTranslatedIngredientsFromString(mappedStrings[Constants.ColumnIndex.TRANSLATED_INGREDIENTS]),
            totalTimeInMins = mappedStrings[Constants.ColumnIndex.TOTAL_TIME_IN_MINS].toInt(),
            cuisine = mappedStrings[Constants.ColumnIndex.CUISINE],
            translatedInstructions = getInstructionsFromString(mappedStrings[Constants.ColumnIndex.TRANSLATED_INSTRUCTIONS]),
            url = mappedStrings[Constants.ColumnIndex.URL],
            cleanedIngredients = getCleanIngredientsFromString(mappedStrings[Constants.ColumnIndex.CLEANED_INGREDIENTS]),
            imageUrl = mappedStrings[Constants.ColumnIndex.IMAGE_URL],
            ingredientCount = mappedStrings[Constants.ColumnIndex.INGREDIENT_COUNT].toInt()
        )

    private fun getTranslatedIngredientsFromString(ingredients: String) =
        ingredients.replace(" - ", " ").split("-")

    private fun getCleanIngredientsFromString(ingredients: String) =
        ingredients.split("-")

    private fun getInstructionsFromString(ingredients: String) =
        ingredients.removeSurrounding("\"").split(".").toMutableList().apply { removeLast() }
}