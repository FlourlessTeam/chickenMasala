package com.example.chickenmasala.data.entities

data class Recipe(
    val translatedRecipeName: String,
    val translatedIngredients: String,
    val totalTimeInMins: Int,
    val cuisine: String,
    val translatedInstructions: String,
    val url: String,
    val cleanedIngredients: String,
    val imageUrl: String,
    val ingredientCount: Int
)