package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.ItemSubcategoryBinding
import com.example.chickenmasala.databinding.SubCategoryListItemBinding
import com.example.chickenmasala.entities.Recipe

class SubcategoriesAdapter :
    ListAdapter<Recipe, SubcategoriesAdapter.ViewHolder>(SubCategoriesDiffUtil()) {
    class ViewHolder(private val binding: SubCategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.mealNameTextView.text = recipe.translatedRecipeName
            binding.mealCookTimeTextView.text = recipe.totalTimeInMins.toString() + " mins"
            Glide.with(binding.mealImageView.context).load(recipe.imageUrl)
                .into(binding.mealImageView)
            if (recipe.isFavourite) {
                Glide.with(binding.favoriteImageView.context).load(R.drawable.favourite)
                    .override(42, 42).into(binding.favoriteImageView)
            } else {
                Glide.with(binding.favoriteImageView.context).load(R.drawable.favourite_fill)
                    .override(42, 42).into(binding.favoriteImageView)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    SubCategoryListItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SubCategoriesDiffUtil : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.translatedRecipeName == newItem.translatedRecipeName
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

    }
}