package com.example.chickenmasala.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenmasala.R

class ForYouViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem) {
    val recipeImage = viewItem.findViewById<View>(R.id.recipeImage)
    val recipeNameTitle = viewItem.findViewById<View>(R.id.RecipeNameTitle)
    val singleCuisine = viewItem.findViewById<View>(R.id.single_cuisine)
    val singleTime = viewItem.findViewById<View>(R.id.single_time)

}