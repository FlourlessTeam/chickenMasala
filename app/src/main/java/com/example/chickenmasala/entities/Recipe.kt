package com.example.chickenmasala.entities

data class Recipe(
    val translatedRecipeName: String,
    val translatedIngredients: List<String>,
    val totalTimeInMins: Int,
    val cuisine: String,
    val translatedInstructions: List<String>,
    val url: String,
    val cleanedIngredients: List<String>,
    val imageUrl: String,
    val ingredientCount: Int,
    var isFavourite:Boolean = false
)