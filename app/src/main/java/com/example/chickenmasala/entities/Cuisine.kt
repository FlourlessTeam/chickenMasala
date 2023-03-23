package com.example.chickenmasala.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Cuisine(val name: String, val recipes: List<Recipe>): Parcelable
