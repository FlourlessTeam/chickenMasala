package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.SearchResultBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.SearchRecipes
import com.example.chickenmasala.ui.adapters.RecipesAdapter

class SearchResultFragment : BaseFragment<SearchResultBinding>(SearchResultBinding::inflate) {

    private val dataManager by lazy { DataManager(requireContext()) }
    private val searchRecipes by lazy { SearchRecipes(dataManager) }
    private val recipesAdapter = RecipesAdapter()

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
                    handleSearchResult(searchRecipes.limitExecute(newText))
                } else {
                    showEmptyState()
                }
                return true
            }
        })
    }

    private fun handleSearchResult(searchResult: List<Recipe>) {
        when {
            searchResult.isNotEmpty() -> showSearchResults(searchResult)
            else -> showEmptyState()
        }
    }

    private fun showSearchResults(searchResult: List<Recipe>) {
        binding.apply {
            imageTea.isVisible = false
            textDetails.isVisible = false
            textEmptyList.isVisible = false
            recyclerView.isVisible = true
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = recipesAdapter
            }
            recipesAdapter.submitList(searchResult)
        }
    }

    private fun showEmptyState() {
        binding.apply {
            imageTea.isVisible = true
            textDetails.isVisible = true
            textEmptyList.isVisible = true
            recyclerView.isVisible = false
        }
    }
}
