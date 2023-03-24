package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentIngredientsBinding
import com.example.chickenmasala.interactors.GetAllRecipes
import com.example.chickenmasala.ui.adapters.IngredientsAdapter

class IngredientsFragment :
    BaseFragment<FragmentIngredientsBinding>(FragmentIngredientsBinding::inflate) {

    private val dataManager by lazy { DataManager(requireContext()) }
    private val getAllRecipes by lazy { GetAllRecipes(dataManager) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeName = arguments?.getString(RECIPE_NAME_KEY)

        val recipe = getAllRecipes.execute().find { it.translatedRecipeName == recipeName }

        val adapter = IngredientsAdapter(recipe!!.translatedIngredients)
        binding.ingredientRecycler.adapter = adapter
    }

    companion object {
        const val RECIPE_NAME_KEY = "recipe_name"
        fun newInstance(recipeName: String) = IngredientsFragment().apply {
            arguments = Bundle().apply {
                putString(RECIPE_NAME_KEY, recipeName)
            }
        }
    }

}



