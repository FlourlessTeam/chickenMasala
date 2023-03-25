package com.example.chickenmasala.ui

import com.example.chickenmasala.entities.Recipe

interface RecipeInteractionListener {
    fun onRecipeClicked(recipe: Recipe)
}