package com.example.chickenmasala.ui.adapters.homeadapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.HomeCuisineCardBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.ui.fragments.CuisinesFragment
import com.example.chickenmasala.ui.fragments.navigationbarfragments.HomeFragment
import com.example.chickenmasala.ui.helpers.CuisineDiffCallback
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener

class HomeCuisinesAdapter(
    private val interactionListener: HomeInteractionListener
) : BaseHomeAdapter<Cuisine, HomeCuisinesAdapter.ViewHolder>(CuisineDiffCallback) {


    override val containerTitle: String
        get() = "Cuisines"

    override fun handleViewAllTransition(view: View) {
        CuisinesFragment().startTransaction((interactionListener as HomeFragment).requireActivity())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_cuisine_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCuisine = getItem(position)
        holder.bind(currentCuisine, interactionListener)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = HomeCuisineCardBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(cuisine: Cuisine, interactionListener: HomeInteractionListener) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(cuisine.recipes[0].imageUrl)
                    .into(cuisineImage)
                cuisineName.text = cuisine.name
                cuisinesRecipes.text =
                    "${cuisine.recipes.size} ${FastRecipesAdapter.ITEMS_SUFFIX}"
                cuisineCard.setOnClickListener {
                    interactionListener.onCuisineClicked(cuisine)
                }
            }
        }
    }
}
