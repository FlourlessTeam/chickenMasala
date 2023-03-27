package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.SubCategoryListItemBinding
import com.example.chickenmasala.entities.Recipe

class SubcategoriesAdapter(private val subcategoryListener: SubcategoryListener) :
    ListAdapter<Recipe, SubcategoriesAdapter.ViewHolder>(SubCategoriesDiffUtil()) {
    class ViewHolder(private val binding: SubCategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe, subcategoryListener: SubcategoryListener) {
            binding.root.setOnClickListener { subcategoryListener.onClick(recipe) }
            binding.textMeal.text = recipe.translatedRecipeName
            binding.textPrepareTime.text = recipe.totalTimeInMins.toString() + " min"
            Glide.with(binding.imageMeal.context).load(recipe.imageUrl).into(binding.imageMeal)
            if (recipe.isFavourite) {
                Glide.with(binding.imageHeart.context).load(R.drawable.favourite).override(24, 24)
                    .into(binding.imageHeart)
            } else {
                Glide.with(binding.imageHeart.context).load(R.drawable.favourite_fill)
                    .override(24, 24).into(binding.imageHeart)
            }
            binding.imageHeart.setOnClickListener {
                if (recipe.isFavourite) {
                    Glide.with(binding.imageHeart.context).load(R.drawable.favourite_fill)
                        .override(24, 24).into(binding.imageHeart)
                } else {
                    Glide.with(binding.imageHeart.context).load(R.drawable.favourite)
                        .override(24, 24).into(binding.imageHeart)
                }
                recipe.isFavourite = !recipe.isFavourite
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
        holder.bind(getItem(position), subcategoryListener)
    }

    class SubCategoriesDiffUtil : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.translatedRecipeName == newItem.translatedRecipeName
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

    }

    class SubcategoryListener(private val onClickListener: (Recipe) -> Unit) {
        fun onClick(recipe: Recipe) = onClickListener(recipe)
    }
}