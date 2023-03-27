package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentSubcategoryBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.ui.adapters.SubcategoriesAdapter

class SubcategoryFragment(private val cuisine: Cuisine) :
    BaseFragment<FragmentSubcategoryBinding>(FragmentSubcategoryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarSubCategory.title = cuisine.name
        val meals = cuisine.recipes
        val adapter = SubcategoriesAdapter(SubcategoriesAdapter.SubcategoryListener {
            DetailsFragment(it).startFragmentTransaction(requireActivity())
        })
        binding.recyclerViewSubCategory.adapter = adapter
        adapter.submitList(meals)
    }

    fun startFragmentTransaction(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, this, TAG)
        fragmentTransaction.commit()
    }

    companion object {
        const val TAG = "Sub Category Fragment Tag"
    }

}