package com.example.chickenmasala.ui.fragments.navigationbarfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentSearchResultBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.SearchRecipes
import com.example.chickenmasala.ui.adapters.RecipesAdapter
import com.example.chickenmasala.ui.fragments.BottomSheetFragment
import com.example.chickenmasala.ui.fragments.detailsscreenfragment.DetailsFragment
import com.example.chickenmasala.ui.interfaces.BaseFragment
import com.example.chickenmasala.ui.interfaces.BottomSheetListener
import com.example.chickenmasala.ui.interfaces.RecipeInteractionListener

class SearchFragment :
    BaseFragment<FragmentSearchResultBinding>(FragmentSearchResultBinding::inflate),
    RecipeInteractionListener, BottomSheetListener {
    private var cookingTime = Int.MAX_VALUE
    private var ingredientCount = Int.MAX_VALUE
    private val dataManager by lazy { DataManager(requireContext()) }
    private val searchRecipes by lazy { SearchRecipes(dataManager) }
    private val recipesAdapter = RecipesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSearchBarBehavior()
    }
    private fun setupSearchBarBehavior() {
        binding.filterButton.setOnClickListener {
            if (!BottomSheetFragment.isBottomSheetInResumeMood) {
                val bottomSheetFragment = BottomSheetFragment(this@SearchFragment)
                bottomSheetFragment.show(childFragmentManager, "tag")
            }

        }
        binding.searchViewResult.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String) = startNewSearch(query)
                override fun onQueryTextChange(query: String) = startNewSearch(query)
            })
    }

    private fun startNewSearch(query: String): Boolean {
        handleSearchResult(
            searchRecipes.executeAdvancedSearchBasedOnQuery(
                query,
                cookingTime,
                ingredientCount
            )
        )
        return true
    }
    private fun handleSearchResult(result: List<Recipe>) {
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

    override fun onRecipeClicked(recipe: Recipe) {
        DetailsFragment.newInstance(recipe).startFragmentTransaction(requireActivity())
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onFavoriteClicked(recipe: Recipe) {
        recipe.isFavourite = !recipe.isFavourite
        recipesAdapter.notifyDataSetChanged()
    }
    override fun onBottomSheetResultButtonClicked(cookingTime: Int, ingredientCount: Int) {
        this.ingredientCount = ingredientCount
        this.cookingTime = cookingTime
        startNewSearch("")
    }


}
