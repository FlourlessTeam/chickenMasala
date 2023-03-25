package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentInstructionsBinding
import com.example.chickenmasala.interactors.GetAllRecipes
import com.example.chickenmasala.ui.adapters.InstructionsAdapter


class InstructionsFragment :
    BaseFragment<FragmentInstructionsBinding>(FragmentInstructionsBinding::inflate) {

    private val dataManager by lazy { DataManager(requireContext()) }
    private val getAllRecipes by lazy { GetAllRecipes(dataManager) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeName = arguments?.getString(RECIPE_NAME_KEY)

        val recipe = getAllRecipes.execute().find { it.translatedRecipeName == recipeName }

        val adapter = InstructionsAdapter(recipe!!.translatedInstructions)
        binding.instructionRecyclerView.adapter = adapter
    }

    companion object {
        const val RECIPE_NAME_KEY = "recipe_name"
        fun newInstance(recipeName: String) = InstructionsFragment().apply {
            arguments = Bundle().apply {
                putString(RECIPE_NAME_KEY, recipeName)
            }
        }
    }
}