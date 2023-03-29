package com.example.chickenmasala.ui.fragments.detailsscreenfragment

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentIngredientsBinding
import com.example.chickenmasala.ui.adapters.detailsadapters.IngredientsAdapter
import com.example.chickenmasala.ui.interfaces.BaseFragment

class IngredientsFragment(private val translatedIngredients:List<String>) :
    BaseFragment<FragmentIngredientsBinding>(FragmentIngredientsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = IngredientsAdapter(translatedIngredients)
        binding.recyclerViewIngredients.adapter = adapter
    }

}



