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
import com.example.chickenmasala.databinding.ForYouSingleRecipeCardBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.fragments.SubcategoryFragment
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener


class SpecialRecipesAdapter(
    private val interactionListener: HomeInteractionListener,
) :
    BaseHomeAdapter<Recipe, SpecialRecipesAdapter.RecipeViewHolder>(RecipeDiffCallback) {
    override val containerTitle: String
        get() = "Special"

    override fun handleViewAllTransition(view: View) {
        SubcategoryFragment.newInstance(Cuisine(containerTitle, currentList))
            .startTransaction((interactionListener as Fragment).requireActivity())

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.for_you_single_recipe_card, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        changeOnData(holder, recipe)
        holder.binding.favIcon.setOnClickListener { favouriteCallBack(it as ImageView, recipe) }
        holder.binding.root.setOnClickListener { interactionListener.onRecipeClicked(recipe) }

    }

    private fun changeOnData(
        holder: RecipeViewHolder,
        currentRecipe: Recipe
    ) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(currentRecipe.imageUrl).into(imageRecipes)
            textRecipesName.text = currentRecipe.translatedRecipeName
            textCuisineName.text = currentRecipe.cuisine
        }
    }

    private fun favouriteCallBack(favIcon: ImageView, recipe: Recipe) {
        favIcon.setOnClickListener {
            recipe.isFavourite = !recipe.isFavourite
            (it as ImageView).setImageResource(if (recipe.isFavourite) R.drawable.favorite_icon_filled else R.drawable.favorite_icon)
        }
    }

    inner class RecipeViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ForYouSingleRecipeCardBinding.bind(viewItem)

    }

    object RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.translatedRecipeName == newItem.translatedRecipeName
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}
