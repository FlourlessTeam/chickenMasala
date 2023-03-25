package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentSubcategoryBinding
import com.example.chickenmasala.interactors.GetAllRecipes
import com.example.chickenmasala.ui.adapters.SubcategoriesAdapter

class SubcategoryFragment :
    BaseFragment<FragmentSubcategoryBinding>(FragmentSubcategoryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SubcategoriesAdapter()
        binding.recyclerViewSubCategory.adapter = adapter
        val meals = GetAllRecipes(DataManager(requireContext())).execute()
        adapter.submitList(meals)
    }


}