package com.example.chickenmasala.util

import com.example.chickenmasala.data.entities.Recipe
import java.io.BufferedReader

class CsvParser{
    fun convertToListOfMappedStrings (buffer: BufferedReader):List<Recipe>{
        var counter = 0
        val list = mutableListOf<String>()
        val x = buffer.readText().split(",").forEach {
            if (counter != 0 && counter % 8 == 0) {
                list.addAll(it.lines())
            } else {
                list.add(it)
            }
            counter++
        }
        return convertMappedStringsToRecipes(list)
    }

    fun convertMappedStringsToRecipes(mappedStrings:List<String>):List<Recipe>{
        var counter = 0
        val recipes = mutableListOf<Recipe>()
        while (counter + 8 < mappedStrings.size) {
            recipes.add(setRecipe(mappedStrings.subList(counter, counter+9)))
            counter += 9
        }
        return recipes
    }


    fun setRecipe(mappedStrings: List<String>) =
        Recipe(
            translatedRecipeName = mappedStrings[Constants.ColumnIndex.TRANSLATED_RECIPE_NAME],
            translatedIngredients = mappedStrings[Constants.ColumnIndex.TRANSLATED_INGREDIENTS],
            totalTimeInMins = mappedStrings[Constants.ColumnIndex.TOTAL_TIME_IN_MINS].toInt(),
            cuisine = mappedStrings[Constants.ColumnIndex.CUISINE],
            translatedInstructions = mappedStrings[Constants.ColumnIndex.TRANSLATED_INSTRUCTIONS],
            url = mappedStrings[Constants.ColumnIndex.URL],
            cleanedIngredients = mappedStrings[Constants.ColumnIndex.CLEANED_INGREDIENTS],
            imageUrl = mappedStrings[Constants.ColumnIndex.IMAGE_URL],
            ingredientCount = mappedStrings[Constants.ColumnIndex.INGREDIENT_COUNT].toInt()
        )

}