package com.example.chickenmasala.util

import com.example.chickenmasala.data.entities.Recipe

class CsvParser {
    fun parseRecipeFromCsvLine(csvLine: String): Recipe {
        val tokens = csvLine.split(",")
        return Recipe(
            translatedRecipeName = tokens[Constants.ColumnIndex.TRANSLATED_RECIPE_NAME],
            translatedIngredients = tokens[Constants.ColumnIndex.TRANSLATED_INGREDIENTS],
            totalTimeInMins = tokens[Constants.ColumnIndex.TOTAL_TIME_IN_MINS].toInt(),
            cuisine = tokens[Constants.ColumnIndex.CUISINE],
            translatedInstructions = tokens[Constants.ColumnIndex.TRANSLATED_INSTRUCTIONS],
            url = tokens[Constants.ColumnIndex.URL],
            cleanedIngredients = tokens[Constants.ColumnIndex.CLEANED_INGREDIENTS],
            imageUrl = tokens[Constants.ColumnIndex.IMAGE_URL],
            ingredientCount = tokens[Constants.ColumnIndex.INGREDIENT_COUNT].toInt()
        )
    }
}