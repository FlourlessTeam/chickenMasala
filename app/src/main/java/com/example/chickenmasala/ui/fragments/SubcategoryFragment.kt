package com.example.chickenmasala.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentSubcategoryBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.adapters.SubcategoriesAdapter
import com.example.chickenmasala.ui.fragments.detailsscreenfragment.DetailsFragment
import com.example.chickenmasala.ui.interfaces.AppbarFragment
import com.example.chickenmasala.ui.interfaces.SubcategoryListener

class SubcategoryFragment :
    AppbarFragment<FragmentSubcategoryBinding>(FragmentSubcategoryBinding::inflate),
    SubcategoryListener {
    private lateinit var cuisine: Cuisine
    private lateinit var adapter: SubcategoriesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAppbarBackButton(binding.toolbarSubCategory)
        arguments.let {
            cuisine = it?.getParcelable(TAG)!!
        }
        binding.toolbarSubCategory.title = cuisine.name
        val meals = cuisine.recipes
        adapter = SubcategoriesAdapter(this)
        binding.recyclerViewSubCategory.adapter = adapter
        adapter.submitList(meals)
    }

    companion object {

        const val TAG = "SubcategoryFragment TAG"
        fun newInstance(cuisine: Cuisine) = SubcategoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TAG, cuisine)
            }
        }
    }

    override fun onClick(recipe: Recipe) {
        DetailsFragment.newInstance(recipe).startFragmentTransaction(requireActivity())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onFavouriteClick(recipe: Recipe) {
        recipe.isFavourite = !recipe.isFavourite
        adapter.notifyDataSetChanged()

    }

}
