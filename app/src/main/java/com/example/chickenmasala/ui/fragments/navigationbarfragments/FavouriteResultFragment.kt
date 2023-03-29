package com.example.chickenmasala.ui.fragments.navigationbarfragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentFavouriteResultBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.GetFavoritesRecipes
import com.example.chickenmasala.ui.interfaces.RecipeInteractionListener
import com.example.chickenmasala.ui.adapters.RecipesAdapter
import com.example.chickenmasala.ui.fragments.detailsscreenfragment.DetailsFragment
import com.example.chickenmasala.ui.interfaces.BaseFragment

class FavouriteResultFragment :
    BaseFragment<FragmentFavouriteResultBinding>(FragmentFavouriteResultBinding::inflate) {

    private val dataManger by lazy { DataManager(requireContext()) }
    private val getFavoritesRecipes by lazy { GetFavoritesRecipes(dataManger) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        handleFavouriteResults()
    }

    private fun handleFavouriteResults() {
        try {
            val favouriteResults = getFavoritesRecipes.execute()
            Log.d(TAG_Favourite_Fragment, "onViewCreated: ${favouriteResults.size}")
            when {
                favouriteResults.isNotEmpty() -> showFavouriteResults(favouriteResults)
                else -> showEmptyState()
            }
        } catch (e: Exception) {
            Log.d(TAG_Favourite_Exception, "Error fetching favourite results: ${e.message}", e)
            showErrorDialog("An error occurred while fetching favourite results. Please try again later.")
        }
    }

    @Suppress("SameParameterValue")
    private fun showErrorDialog(errorMessage: String) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(errorMessage)
            .setPositiveButton("OK") { _, _ -> }
            .create()
        dialog.show()
    }

    private fun showFavouriteResults(favouriteResults: List<Recipe>) {
        with(binding) {
            emptyView.apply {
                imageFavouriteEmpty.isVisible = false
                textFavouriteEmpty.isVisible = false
                textEmptyFavouriteDetails.isVisible = false
            }
            recyclerViewFavourite.apply {
                isVisible = true
                adapter = getRecipesAdapter().apply {
                    submitList(favouriteResults)
                }
            }
        }
    }

    private fun showEmptyState() {
        with(binding) {
            emptyView.apply {
                imageFavouriteEmpty.isVisible = true
                textFavouriteEmpty.isVisible = true
                textEmptyFavouriteDetails.isVisible = true
            }
            recyclerViewFavourite.isVisible = false
        }
    }

    private fun getRecipesAdapter(): RecipesAdapter {
        val interactionListener = object : RecipeInteractionListener {
            override fun onRecipeClicked(recipe: Recipe) {
                DetailsFragment.newInstance(recipe).startFragmentTransaction(requireActivity())
            }

            override fun onFavoriteClicked(recipe: Recipe) {

            }

        }
        return RecipesAdapter(interactionListener)
    }

    companion object {
        const val TAG_Favourite_Fragment = "Favorite Fragment Tag"
        const val TAG_Favourite_Exception = "Favorite Exception Tag"
    }
}