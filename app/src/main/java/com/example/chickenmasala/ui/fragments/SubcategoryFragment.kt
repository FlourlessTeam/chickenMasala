package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentSubcategoryBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.ui.adapters.SubcategoriesAdapter
import com.example.chickenmasala.ui.fragments.interfaces.AppbarFragment

class SubcategoryFragment(private val cuisine: Cuisine) :
    AppbarFragment<FragmentSubcategoryBinding>(FragmentSubcategoryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAppbarBackButton(binding.toolbarSubCategory)
        binding.toolbarSubCategory.title = cuisine.name
        val meals = cuisine.recipes
        val adapter = SubcategoriesAdapter(SubcategoriesAdapter.SubcategoryListener {
            DetailsFragment(it).startFragmentTransaction(requireActivity())
        })
        binding.recyclerViewSubCategory.adapter = adapter
        adapter.submitList(meals)
    }




}