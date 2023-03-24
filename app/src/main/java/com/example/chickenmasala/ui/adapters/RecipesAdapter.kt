package com.example.chickenmasala.ui.adapters

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
        holder.bind(recipe, position)
    }

    class RecipeViewHolder(private val binding: ItemRecipeTileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe, position: Int) {
            binding.apply {
                textItem.text = recipe.translatedRecipeName
                textCookingTime.text = recipe.totalTimeInMins.toString() + " min"
                Glide.with(root.context).load(recipe.imageUrl).into(imageItem)
                iconFavourite.setImageResource(if (recipe.isFavourite) R.drawable.favourite else R.drawable.favourite_fill)
                iconFavourite.setOnClickListener {
                    recipe.isFavourite = !recipe.isFavourite
                    iconFavourite.setImageResource(if (recipe.isFavourite) R.drawable.favourite else R.drawable.favourite_fill)
                    bindingAdapter?.notifyItemChanged(position)
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
