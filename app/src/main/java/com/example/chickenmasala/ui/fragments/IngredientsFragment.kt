package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import com.example.chickenmasala.databinding.FragmentIngredientsBinding

class IngredientsFragment : BaseFragment<FragmentIngredientsBinding>(FragmentIngredientsBinding::inflate) {



    override fun onStart() {
        super.onStart()
//        arguments?.let {
//            val ingredients = it.getString("aa")
//            binding.textIngredients.text = ingredients?.formatIngredients()
//        }
    }

    private fun String.formatIngredients(): String {
        return replace("-", "\n").lines().mapIndexed { index, line ->
            "${index + 1}. $line"
        }.joinToString(separator = "\n\n")

    }

    companion object {
        fun newInstance(ingredients: String) = IngredientsFragment().apply {
            arguments = Bundle().apply {
                putString("aa", ingredients)
            }
        }
    }

}



