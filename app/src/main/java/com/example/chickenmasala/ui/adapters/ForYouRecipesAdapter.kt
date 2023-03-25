package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.ForYouSingleRecipeCardBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.RecipeInteractionListener


class ForYouRecipesAdapter(
    private val forYouRecipes: List<Recipe>,
   private val interactionListener: RecipeInteractionListener
) :
    RecyclerView.Adapter<ForYouRecipesAdapter.ForYouViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForYouViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.for_you_single_recipe_card, parent, false)
        return ForYouViewHolder(view)
    }

    override fun getItemCount(): Int = forYouRecipes.size


    override fun onBindViewHolder(holder: ForYouViewHolder, position: Int) {
        val currentRecipe = forYouRecipes[position]
        changeOnData(holder, currentRecipe)
        holder.binding.ForYouCard.setOnClickListener {
            interactionListener.onRecipeClicked(currentRecipe)

        }
    }

    private fun changeOnData(
        holder: ForYouViewHolder,
        currentRecipe: Recipe
    ) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(currentRecipe.imageUrl).into(recipeImage)
            RecipeNameTitle.text = currentRecipe.translatedRecipeName
            singleCuisine.text = currentRecipe.cuisine
            singleTime.text = currentRecipe.totalTimeInMins.toString()

        }
    }


    inner class ForYouViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ForYouSingleRecipeCardBinding.bind(viewItem)

    }


}