package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Recipe

interface RecipesDataSource {
    fun getAllRecipesData(): List<Recipe>
}