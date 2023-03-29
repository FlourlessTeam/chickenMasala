package com.example.chickenmasala.ui.interfaces

import com.example.chickenmasala.entities.Recipe

interface SubcategoryListener {

    fun onClick(recipe: Recipe)
    fun onFavouriteClick(recipe: Recipe)
}