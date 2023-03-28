package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentSearchResultBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.SearchRecipes
import com.example.chickenmasala.ui.interfaces.RecipeInteractionListener
import com.example.chickenmasala.ui.adapters.RecipesAdapter
import com.example.chickenmasala.ui.interfaces.BaseFragment

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(FragmentSearchResultBinding::inflate) {

    private val dataManager by lazy { DataManager(requireContext()) }
    private val searchRecipes by lazy { SearchRecipes(dataManager) }
    private val recipesAdapter = getRecipesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchViewResult.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    handleSearchResult(searchRecipes.execute(query))
                } else {
                    showEmptyState()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    handleSearchResult(searchRecipes.executeSomeSearchRecipe(newText))
                } else {
                    showEmptyState()
                }
                return true
            }
        })
    }

    private fun handleSearchResult(result: List<Recipe>) {
        //Todo edit this code to optimize computation
        when {
            result.isNotEmpty() -> showSearchResults(result)
            else -> showEmptyState()
        }
    }

    private fun showSearchResults(result: List<Recipe>) {
        with(binding) {
            emptyView.apply {
                imageTea.isVisible = false
                textDetails.isVisible = false
                textEmptyList.isVisible = false
            }
            recyclerView.isVisible = true
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = recipesAdapter
            }
            recipesAdapter.submitList(result)
        }
    }

    private fun showEmptyState() {
        with(binding) {
            emptyView.apply {
                imageTea.isVisible = true
                textDetails.isVisible = true
                textEmptyList.isVisible = true
            }
            recyclerView.isVisible = false
        }
    }
    private  fun getRecipesAdapter():RecipesAdapter{
        val interactionListener=object : RecipeInteractionListener {
            override fun onRecipeClicked(recipe: Recipe) {
                DetailsFragment.newInstance(recipe).startFragmentTransaction(requireActivity())
            }

        }
        return RecipesAdapter(interactionListener)

    }
}
