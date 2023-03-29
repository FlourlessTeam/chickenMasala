package com.example.chickenmasala.ui.adapters.homeadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.HomeCuisineCardBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.ui.fragments.CuisinesFragment
import com.example.chickenmasala.ui.fragments.navigationbarfragments.HomeFragment
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener

class HomeCuisinesAdapter(
    private val interactionListener: HomeInteractionListener
) :
    BaseHomeAdapter<Cuisine, HomeCuisinesAdapter.HomeCuisinesViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Cuisine>() {
        override fun areItemsTheSame(oldItem: Cuisine, newItem: Cuisine): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Cuisine, newItem: Cuisine): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }

    override val containerTitle: String
        get() = "Cuisines"

    override fun handleViewAllTransition(view: View) {
        CuisinesFragment().startTransaction((interactionListener as HomeFragment).requireActivity())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCuisinesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_cuisine_card, parent, false)
        return HomeCuisinesViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeCuisinesViewHolder, position: Int) {
        val currentCuisine = getItem(position)
        holder.bind(currentCuisine, interactionListener)
    }

    inner class HomeCuisinesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = HomeCuisineCardBinding.bind(view)

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
