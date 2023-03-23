package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentSubcategoryBinding
import com.example.chickenmasala.interactors.GetSubcategories

class SubcategoryFragment :
    BaseFragment<FragmentSubcategoryBinding>(FragmentSubcategoryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val meals = GetSubcategories(DataManager(requireContext())).execute()
        var str = ""
        meals.forEach {
            str += "Meal name: ${it.first}, Time: ${it.second}, Image Url: ${it.third}\n"
        }
        binding.subCategoryTextView.text = str
    }


}