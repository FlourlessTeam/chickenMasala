package com.example.chickenmasala.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.ItemRecipeTileBinding
import com.example.chickenmasala.entities.Recipe

class RecipesAdapter : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding =
            ItemRecipeTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    class RecipeViewHolder(private val binding: ItemRecipeTileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(recipe: Recipe) {
            binding.apply {
                textItem.text = recipe.translatedRecipeName
                textCookingTime.text = recipe.totalTimeInMins.toString() + " min"
                Glide.with(root.context).load(recipe.imageUrl).into(imageItem)
                iconFavourite.setOnClickListener {
                    recipe.isFavourite = !recipe.isFavourite
                    binding.iconFavourite.setImageResource(if (recipe.isFavourite) R.drawable.favourite_fill else R.drawable.favourite)
                }

            }
        }
    }

    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.translatedRecipeName == newItem.translatedRecipeName
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}
