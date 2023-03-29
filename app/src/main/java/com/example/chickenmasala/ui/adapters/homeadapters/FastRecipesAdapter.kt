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
import com.example.chickenmasala.interactors.GetRecipesLessThanGivenTime
import com.example.chickenmasala.ui.fragments.SubcategoryFragment
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener

class FastRecipesAdapter(
    private val interactionListener: HomeInteractionListener,
) : BaseHomeAdapter<Recipe, FastRecipesAdapter.RecipeViewHolder>(
    DIFF_CALLBACK
) {
    override val containerTitle: String
        get() = "Fast recipes"

    override fun handleViewAllTransition(view: View) {
        val activity = (interactionListener as Fragment).requireActivity()
        val dataSource = DataManager(activity)
        val recipes = GetRecipesLessThanGivenTime(dataSource).execute(10, Int.MAX_VALUE)
        SubcategoryFragment.newInstance(Cuisine(containerTitle, recipes)).startTransaction(activity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recipe_card, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = getItem(position)
        changeOnData(holder, currentRecipe)
        favouriteCallBack(holder.binding.favIcon, currentRecipe)
        holder.binding.specificRecipeCard.setOnClickListener {
            interactionListener.onRecipeClicked(currentRecipe)
        }
    }

    private fun changeOnData(
        holder: RecipeViewHolder,
        currentRecipe: Recipe
    ) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(currentRecipe.imageUrl).into(imageRecipe)
            textRecipeName.text = currentRecipe.translatedRecipeName
            textCookTime.text = "${currentRecipe.totalTimeInMins} $MINUTES_SUFFIX"
        }
    }

    private fun favouriteCallBack(favIcon: ImageView, recipe: Recipe) {
        favIcon.setOnClickListener {
            recipe.isFavourite = !recipe.isFavourite
            (it as ImageView).setImageResource(
                if (recipe.isFavourite) R.drawable.favorite_icon_filled else R.drawable.favorite_icon
            )

        }
    }

    inner class RecipeViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = CustomRecipeCardBinding.bind(viewItem)
    }

    companion object {
        const val MINUTES_SUFFIX = "min"
        const val ITEMS_SUFFIX = "items"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.translatedRecipeName == newItem.translatedRecipeName
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }
}
