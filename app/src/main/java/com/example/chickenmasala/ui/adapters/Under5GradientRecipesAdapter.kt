package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.CustomeRecipeCardBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.RecipeInteractionListener

class Under5GradientRecipesAdapter(
    private val forYouRecipes: List<Recipe>, private val listener: RecipeInteractionListener
) : RecyclerView.Adapter<Under5GradientRecipesAdapter.Under5GradientRecipesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Under5GradientRecipesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custome_recipe_card, parent, false)
        return Under5GradientRecipesViewHolder(view)
    }

    override fun getItemCount(): Int = forYouRecipes.size


    override fun onBindViewHolder(holder: Under5GradientRecipesViewHolder, position: Int) {
        val currentRecipe = forYouRecipes[position]
        changeOnData(holder, currentRecipe)
        holder.binding.ForYouCard.setOnClickListener {
            listener.onRecipeClicked(currentRecipe)
        }
    }

    private fun changeOnData(
        holder: Under5GradientRecipesViewHolder, currentRecipe: Recipe
    ) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(currentRecipe.imageUrl).into(recipeImage)
            RecipeNameTitle.text = currentRecipe.translatedRecipeName
            singleTime.text = currentRecipe.totalTimeInMins.toString()

        }
    }


    inner class Under5GradientRecipesViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = CustomeRecipeCardBinding.bind(viewItem)

    }

}