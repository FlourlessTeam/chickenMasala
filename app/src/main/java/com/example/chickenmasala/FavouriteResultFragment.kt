package com.example.chickenmasala

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FavouriteResultBinding
import com.example.chickenmasala.interactors.GetFavoritesRecipes
import com.google.android.material.imageview.ShapeableImageView

class FavouriteResultFragment: Fragment() {

    lateinit var binding: FavouriteResultBinding
    private val dataSource = DataManager(requireContext())
    private val getFavoritesRecipes = GetFavoritesRecipes(dataSource)
    private val favouriteRecipes = getFavoritesRecipes.execute()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavouriteResultBinding.inflate(inflater,container,false)
        val emptyView = layoutInflater.inflate(R.layout.empty_favourite_content, binding.listContainer, false ) as ConstraintLayout

        if (favouriteRecipes.isEmpty()) {
            binding.listContainer.removeAllViews()
            binding.listContainer.addView(emptyView)
        } else {
            binding.listContainer.removeAllViews()
            favouriteRecipes.forEach {
                val itemView = layoutInflater.inflate(R.layout.favourite_content, binding.listContainer, false ) as ConstraintLayout
                val recipeName = itemView.findViewById<TextView>(R.id.recipe_name)
                recipeName.text = it.translatedRecipeName

                val recipeImage= itemView.findViewById<ShapeableImageView>(R.id.recipe_image)
                val recipeImageUrl = it.imageUrl
                if (recipeImageUrl.isNotEmpty()) {
                    Glide.with(itemView)
                        .load(recipeImageUrl)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.error)
                        .into(recipeImage)
                }

                val recipeCookingTime = itemView.findViewById<TextView>(R.id.recipe_cooking_time)
                recipeCookingTime.text = it.totalTimeInMins.toString()

                val favoriteIcon = itemView.findViewById<ImageView>(R.id.favourite_icon)
                favoriteIcon.setImageResource(R.drawable.favourite_fill)

                binding.listContainer.addView(itemView)
            }
        }
        return binding.root
    }
}