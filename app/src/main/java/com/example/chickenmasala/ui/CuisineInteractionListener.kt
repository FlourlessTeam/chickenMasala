package com.example.chickenmasala.ui

import com.example.chickenmasala.entities.Cuisine

interface CuisineInteractionListener {
    fun onCuisineClicked(cuisine: Cuisine)
}