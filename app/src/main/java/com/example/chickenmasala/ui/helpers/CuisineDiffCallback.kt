package com.example.chickenmasala.ui.helpers

import androidx.recyclerview.widget.DiffUtil
import com.example.chickenmasala.entities.Cuisine


object CuisineDiffCallback : DiffUtil.ItemCallback<Cuisine>() {
    override fun areItemsTheSame(oldItem: Cuisine, newItem: Cuisine): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Cuisine, newItem: Cuisine): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }
}
