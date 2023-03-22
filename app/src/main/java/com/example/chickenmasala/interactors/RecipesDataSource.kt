package com.example.chickenmasala.interactors

import com.example.chickenmasala.entities.Recipe

interface RecipesDataSource {
   val allRecipesData: List<Recipe>
}