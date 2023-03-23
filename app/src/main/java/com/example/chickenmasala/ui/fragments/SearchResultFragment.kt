package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.SearchResultBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.SearchRecipes

class SearchResultFragment : BaseFragment<SearchResultBinding>(SearchResultBinding::inflate) {


    private val dataManager by lazy { DataManager(requireContext()) }
    private val searchRecipes by lazy { SearchRecipes(dataManager) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        if (searchResult.isNotEmpty()) {
            showSearchResults(searchResult)
        } else {
            showEmptyState()
        }
    }

    private fun showSearchResults(searchResult: List<Recipe>) {
        binding.teaImage.visibility = View.GONE
        binding.detailsText.visibility = View.GONE
        binding.emptyText.visibility = View.GONE
        binding.listContainer.removeAllViews()

        for (item in searchResult) {
            val itemView = layoutInflater.inflate(R.layout.search_content, binding.listContainer, false) as ConstraintLayout
            val itemTextView = itemView.findViewById<TextView>(R.id.kofta_text)
            val itemImageView= itemView.findViewById<ImageView>(R.id.kofta_image)
            val itemFavView= itemView.findViewById<ImageView>(R.id.favourite_icon)
            val itemTime=itemView.findViewById<TextView>(R.id.cooking_time_text)
            itemTextView.text = item.translatedRecipeName
            itemTime.text=item.totalTimeInMins.toString()+" min"
            Glide.with(this)
                .load(item.imageUrl)
                .into(itemImageView)
            itemFavView.setOnClickListener {
                item.isFavourite = !item.isFavourite
                itemFavView.setBackgroundResource(if (item.isFavourite) R.drawable.favourite else R.drawable.favourite_fill)
            }

            binding.listContainer.addView(itemView)
        }
    }
    private fun showEmptyState() {
        binding.teaImage.visibility = View.VISIBLE
        binding.detailsText.visibility = View.VISIBLE
        binding.emptyText.visibility = View.VISIBLE
        binding.listContainer.removeAllViews()
    }
}
