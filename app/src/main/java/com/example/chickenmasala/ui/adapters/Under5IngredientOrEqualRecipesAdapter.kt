package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.CustomeRecipeCardBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.HomeInteractionListener

class Under5IngredientOrEqualRecipesAdapter(
    private val forYouRecipes: List<Recipe>, private val listener: HomeInteractionListener
) : RecyclerView.Adapter<Under5IngredientOrEqualRecipesAdapter.Under5IngredientOrEqualRecipesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Under5IngredientOrEqualRecipesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custome_recipe_card, parent, false)
        return Under5IngredientOrEqualRecipesViewHolder(view)
    }

    override fun getItemCount(): Int = forYouRecipes.size


    override fun onBindViewHolder(holder: Under5IngredientOrEqualRecipesViewHolder, position: Int) {
        val currentRecipe = forYouRecipes[position]
        changeOnData(holder, currentRecipe)
        holder.binding.specificRecipeCard.setOnClickListener {
            listener.onRecipeClicked(currentRecipe)
        }
    }

    private fun changeOnData(
        holder: Under5IngredientOrEqualRecipesViewHolder, currentRecipe: Recipe
    ) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(currentRecipe.imageUrl).into(imageRecipe)
            textRecipeName.text = currentRecipe.translatedRecipeName
            textCookTime.text =
                "${currentRecipe.totalTimeInMins} ${Under20MinOrEqualRecipesAdapter.MINUTES_SUFFIX}"

        }
    }


    inner class Under5IngredientOrEqualRecipesViewHolder(viewItem: View) :
        RecyclerView.ViewHolder(viewItem) {
        val binding = CustomeRecipeCardBinding.bind(viewItem)

    }

}