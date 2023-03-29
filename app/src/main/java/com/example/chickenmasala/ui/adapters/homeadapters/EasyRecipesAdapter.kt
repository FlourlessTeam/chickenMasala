package com.example.chickenmasala.ui.adapters.homeadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.CustomRecipeCardBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.GetRecipesLessThanGivenIngredient
import com.example.chickenmasala.ui.fragments.SubcategoryFragment
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener

class EasyRecipesAdapter(
    private val interactionListener: HomeInteractionListener,
) : BaseHomeAdapter<Recipe, EasyRecipesAdapter.ViewHolder>(
    DiffCallback()
) {
    override val containerTitle: String
        get() = "Easy recipes"

    override fun handleViewAllTransition(view: View) {
        val activity = (interactionListener as Fragment).requireActivity()
        val dataSource = DataManager(activity)
        val recipes = GetRecipesLessThanGivenIngredient(dataSource).execute(10, Int.MAX_VALUE)
        SubcategoryFragment.newInstance(Cuisine(containerTitle,recipes)).startTransaction(activity)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_recipe_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecipe = getItem(position)
        holder.bind(currentRecipe)
    }

    inner class ViewHolder(viewItem: View) :
        RecyclerView.ViewHolder(viewItem) {
        private val binding = CustomRecipeCardBinding.bind(viewItem)

        init {
            binding.specificRecipeCard.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { recipe ->
                    interactionListener.onRecipeClicked(recipe)
                }
            }
        }

        fun bind(recipe: Recipe) {
            with(binding) {
                Glide.with(itemView.context).load(recipe.imageUrl).into(imageRecipe)
                textRecipeName.text = recipe.translatedRecipeName
                textCookTime.text =
                    "${recipe.totalTimeInMins} ${FastRecipesAdapter.MINUTES_SUFFIX}"
                favouriteCallBack(favIcon, recipe)
            }
        }

        private fun favouriteCallBack(favIcon: ImageView, recipe: Recipe) {
            favIcon.setOnClickListener {
                recipe.isFavourite = !recipe.isFavourite
                favIcon.setImageResource(if (recipe.isFavourite) R.drawable.favourite else R.drawable.favourite_outline)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.translatedRecipeName == newItem.translatedRecipeName
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}
