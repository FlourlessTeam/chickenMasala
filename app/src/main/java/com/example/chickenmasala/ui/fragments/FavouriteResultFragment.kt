package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FavouriteResultBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.GetFavoritesRecipes
import com.example.chickenmasala.ui.adapters.RecipesAdapter

class FavouriteResultFragment :
    BaseFragment<FavouriteResultBinding>(FavouriteResultBinding::inflate) {

    private val dataManger by lazy { DataManager(requireContext()) }
    private val getFavoritesRecipes by lazy { GetFavoritesRecipes(dataManger) }
    private val recipesAdapter = RecipesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val favouriteResults = getFavoritesRecipes.execute()
        Log.d("FavouriteResultFragment", "onViewCreated: ${favouriteResults.size}")
        when {
            favouriteResults.isNotEmpty() -> showFavouriteResults(favouriteResults)
            else -> showEmptyState()
        }
    }

    private fun showFavouriteResults(favouriteResults: List<Recipe>) {
        binding.apply {
            emptyView.apply {
                imageFavouriteEmpty.isVisible = false
                textFavouriteEmpty.isVisible = false
                textEmptyFavouriteDetails.isVisible = false
            }
            recyclerViewFavourite.isVisible = true
            recyclerViewFavourite.adapter = recipesAdapter
            recipesAdapter.submitList(favouriteResults)
        }
    }

    private fun showEmptyState() {
        binding.apply {
            emptyView.apply {
                imageFavouriteEmpty.isVisible = true
                textFavouriteEmpty.isVisible = true
                textEmptyFavouriteDetails.isVisible = true
            }
            recyclerViewFavourite.isVisible = false
        }
    }
}