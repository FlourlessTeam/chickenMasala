package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.CustomeRecipeCardBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener
import android.widget.ImageView
import com.example.chickenmasala.data.DataManager


class Under20MinOrEqualRecipesAdapter(
    private val forYouRecipes: List<Recipe>,
    private val listener: HomeInteractionListener,
    private val dataManager: DataManager
) : RecyclerView.Adapter<Under20MinOrEqualRecipesAdapter.Under20MinRecipesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Under20MinRecipesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custome_recipe_card, parent, false)
        return Under20MinRecipesViewHolder(view)
    }

    override fun getItemCount(): Int = forYouRecipes.size


    override fun onBindViewHolder(holder: Under20MinRecipesViewHolder, position: Int) {
        val currentRecipe = forYouRecipes[position]
        changeOnData(holder, currentRecipe)
        favouriteCallBack(holder.binding.favIcon, currentRecipe)
        holder.binding.specificRecipeCard.setOnClickListener {
            listener.onRecipeClicked(currentRecipe)
        }
    }

    private fun changeOnData(
        holder: Under20MinRecipesViewHolder, currentRecipe: Recipe
    ) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(currentRecipe.imageUrl).into(imageRecipe)
            textRecipeName.text = currentRecipe.translatedRecipeName
            textCookTime.text = "${currentRecipe.totalTimeInMins} $MINUTES_SUFFIX"
        }
    }

    private fun favouriteCallBack(favIcon: ImageView, recipe: Recipe) {
        favIcon.setOnClickListener {
            recipe.isFavourite = !recipe.isFavourite
            (it as ImageView).setImageResource(if (recipe.isFavourite) R.drawable.favorite_icon_filled else R.drawable.favorite_icon)
            if (!recipe.isFavourite) {
                dataManager.addToFavoritesRecipes(recipe)
            } else {
                dataManager.removeFromFavoritesRecipes(recipe)
            }
        }
    }

    inner class Under20MinRecipesViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = CustomeRecipeCardBinding.bind(viewItem)

    }

    companion object {
        const val MINUTES_SUFFIX = "min"
        const val ITEMS_SUFFIX = "items"
    }

}