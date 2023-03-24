package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.entities.Recipe
import android.widget.TextView

class ForYouRecipesAdapter(private val forYouRecipes: List<Recipe>) :
    RecyclerView.Adapter<ForYouViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForYouViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_recipe_card, parent, false)
        return ForYouViewHolder(view)
    }

    override fun getItemCount(): Int = forYouRecipes.size


    override fun onBindViewHolder(holder: ForYouViewHolder, position: Int) {
        val currentRecipe = forYouRecipes[position]
        loadRecipeData(currentRecipe, holder)
        loadRecipeImage(currentRecipe, holder)
    }

    private fun loadRecipeData(currentRecipe: Recipe, holder: ForYouViewHolder) {
        holder.apply {
            recipeNameTitle.text = currentRecipe.translatedRecipeName
            singleCuisine.text = currentRecipe.cuisine
            singleTime.text = currentRecipe.totalTimeInMins.toString()
        }
    }

    private fun loadRecipeImage(currentRecipe: Recipe, holder: ForYouViewHolder) {
        Glide.with(holder.itemView.context).load(currentRecipe.imageUrl)
            .into(holder.recipeImage as ImageView)
    }

}