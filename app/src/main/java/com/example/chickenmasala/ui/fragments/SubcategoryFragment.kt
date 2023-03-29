package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentSubcategoryBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.ui.adapters.SubcategoriesAdapter
import com.example.chickenmasala.ui.fragments.detailsscreenfragment.DetailsFragment
import com.example.chickenmasala.ui.interfaces.AppbarFragment

@Suppress("DEPRECATION")
class SubcategoryFragment :
    AppbarFragment<FragmentSubcategoryBinding>(FragmentSubcategoryBinding::inflate) {
    private lateinit var cuisine: Cuisine
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAppbarBackButton(binding.toolbarSubCategory)
        arguments.let {
            cuisine = it?.getParcelable(TAG)!!
        }
        binding.toolbarSubCategory.title = cuisine.name
        val meals = cuisine.recipes
        val adapter = SubcategoriesAdapter(SubcategoriesAdapter.SubcategoryListener {
            DetailsFragment.newInstance(it).startFragmentTransaction(requireActivity())
        })
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


}