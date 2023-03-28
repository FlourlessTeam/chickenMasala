package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentIngredientsBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.adapters.IngredientsAdapter
import com.example.chickenmasala.ui.interfaces.BaseFragment

class IngredientsFragment :
    BaseFragment<FragmentIngredientsBinding>(FragmentIngredientsBinding::inflate) {
    private lateinit var translatedIngredients: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            translatedIngredients = it.getStringArrayList(TAG)!!
        }
        val adapter = IngredientsAdapter(translatedIngredients)
        binding.recyclerViewIngredients.adapter = adapter
    }
    companion object {
        private const val TAG = "translatedIngredients"
        fun newInstance(translatedIngredients: List<String>) = IngredientsFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(TAG, ArrayList(translatedIngredients))
            }
        }
    }
}



