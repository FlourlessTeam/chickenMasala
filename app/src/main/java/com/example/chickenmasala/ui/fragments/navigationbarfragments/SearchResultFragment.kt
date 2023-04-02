package com.example.chickenmasala.ui.fragments.navigationbarfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentSearchResultBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.SearchRecipes
import com.example.chickenmasala.ui.interfaces.RecipeInteractionListener
import com.example.chickenmasala.ui.adapters.RecipesAdapter
import com.example.chickenmasala.ui.fragments.BottomSheetFragment
import com.example.chickenmasala.ui.fragments.detailsscreenfragment.DetailsFragment
import com.example.chickenmasala.ui.interfaces.BaseFragment
import com.example.chickenmasala.ui.interfaces.BottomSheetListener

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(FragmentSearchResultBinding::inflate),RecipeInteractionListener , BottomSheetListener {

    private val dataManager by lazy { DataManager(requireContext()) }
    private val searchRecipes by lazy { SearchRecipes(dataManager) }
    private val recipesAdapter = RecipesAdapter(this)
    private var searchQuery :String = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.filterButton.setOnClickListener {
            if (!searchQuery.isNullOrBlank()) {
                var bottomSheetFragment = BottomSheetFragment(this)
                bottomSheetFragment.show(childFragmentManager, "tag")
            }else{
                Toast.makeText(requireContext() , "Please search first" , Toast.LENGTH_SHORT).show()
            }
        }

        binding.searchViewResult.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    searchQuery = query
                    handleSearchResult(searchRecipes.execute(query))
                } else {
                    showEmptyState()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    searchQuery = newText
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
    override fun onRecipeClicked(recipe: Recipe) {
        DetailsFragment.newInstance(recipe).startFragmentTransaction(requireActivity())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onFavoriteClicked(recipe: Recipe) {
        recipe.isFavourite = !recipe.isFavourite
        recipesAdapter.notifyDataSetChanged()
    }

    override fun onButtonClicked(cookingTime: Int, ingredientCount: Int) {
        val list = searchRecipes.executeWithFilter(searchQuery ,cookingTime, ingredientCount)
        if (list.isNotEmpty()) {
            handleSearchResult(list)
        } else {
            showEmptyState()
        }
    }
}
