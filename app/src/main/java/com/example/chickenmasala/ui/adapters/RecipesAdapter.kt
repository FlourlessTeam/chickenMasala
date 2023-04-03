package com.example.chickenmasala.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.ItemRecipeTileBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.helpers.RecipeDiffCallback
import com.example.chickenmasala.ui.interfaces.RecipeInteractionListener

class RecipesAdapter(private val interactionListener: RecipeInteractionListener) :
    ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding =
            ItemRecipeTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        val recipe = getItem(position)
        holder.bind(recipe)
        holder.binding.root.setOnClickListener {
            interactionListener.onRecipeClicked(recipe)
        }
        holder.binding.iconFavourite.setOnClickListener {
            interactionListener.onFavoriteClicked(recipe)
            notifyItemChanged(position)
        }
    }

    class RecipeViewHolder(val binding: ItemRecipeTileBinding) :
        RecyclerView.ViewHolder(binding.root) {



        @SuppressLint("SetTextI18n")
        fun bind(recipe: Recipe) {
            binding.apply {
                textItem.text = recipe.translatedRecipeName
                textCookingTime.text = "${recipe.totalTimeInMins} min"
                Glide.with(root.context).load(recipe.imageUrl).into(imageItem)
                iconFavourite.setImageResource(
                    if (recipe.isFavourite) R.drawable.favorite_icon_filled else R.drawable.favorite_icon
                )
            }
        }

    }


}
