package com.example.chickenmasala.ui.helpers

import androidx.recyclerview.widget.DiffUtil
import com.example.chickenmasala.entities.Recipe

object RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.translatedRecipeName == newItem.translatedRecipeName
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}
