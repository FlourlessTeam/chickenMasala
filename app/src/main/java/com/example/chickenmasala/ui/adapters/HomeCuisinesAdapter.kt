package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.HomeCuisineCardBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.ui.HomeInteractionListener


class HomeCuisinesAdapter(
    private val cuisines: List<Cuisine>,
    private val interactionListener: HomeInteractionListener
) :
    RecyclerView.Adapter<HomeCuisinesAdapter.HomeCuisinesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCuisinesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_cuisine_card, parent, false)
        return HomeCuisinesViewHolder(view)
    }

    override fun getItemCount(): Int = cuisines.size


    override fun onBindViewHolder(holder: HomeCuisinesViewHolder, position: Int) {
        val currentCuisine = cuisines[position]
        changeOnData(holder, currentCuisine)
        holder.binding.cuisineCard.setOnClickListener {
            interactionListener.onCuisineClicked(currentCuisine)

        }
    }

    private fun changeOnData(
        holder: HomeCuisinesViewHolder,
        currentCuisine: Cuisine
    ) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(currentCuisine.recipes[0].imageUrl)
                .into(cuisineImage)
            cuisineName.text = currentCuisine.name
            cuisinesRecipes.text =
                "${currentCuisine.recipes.size} ${Under20MinOrEqualRecipesAdapter.ITEMS_SUFFIX}"

        }
    }


    inner class HomeCuisinesViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = HomeCuisineCardBinding.bind(viewItem)

    }


}