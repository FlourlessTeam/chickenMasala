package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenmasala.databinding.ItemIngredientBinding


class IngredientsAdapter(private var ingredients: List<String>) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientBinding.inflate(layoutInflater, parent, false)
        return IngredientViewHolder(binding)
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) =
        holder.bind(ingredients[position], position + 1)



    class IngredientViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredients: String, position: Int) {
            binding.textNumberCount.text = "$position."
            binding.textIngredients.text = ingredients.trim()
        }
    }
}