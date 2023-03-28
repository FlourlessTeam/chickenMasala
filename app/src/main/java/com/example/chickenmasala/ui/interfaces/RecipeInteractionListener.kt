package com.example.chickenmasala.ui.interfaces

import com.example.chickenmasala.entities.Recipe

interface RecipeInteractionListener {
    fun onRecipeClicked(recipe: Recipe)
}