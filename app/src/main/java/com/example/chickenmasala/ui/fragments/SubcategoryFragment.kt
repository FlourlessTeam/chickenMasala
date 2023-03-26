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
        val adapter = SubcategoriesAdapter()
        binding.recyclerViewSubCategory.adapter = adapter
        // TODO , you should use the cuisine to get the recipes like that and the same as view all buttons
        val meals = cuisine.recipes
        // TODO: you should change the name to the based on the cuisine name that clicked or based on the category name

//        val meals = GetAllRecipes(DataManager(requireContext())).execute()
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