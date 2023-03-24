package com.example.chickenmasala.ui.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenmasala.R

class ForYouViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
    val recipeImage: View = viewItem.findViewById(R.id.recipeImage)
    val recipeNameTitle: TextView = viewItem.findViewById(R.id.RecipeNameTitle)
    val singleCuisine: TextView = viewItem.findViewById(R.id.single_cuisine)
    val singleTime: TextView = viewItem.findViewById(R.id.single_time)

}