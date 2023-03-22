package com.example.chickenmasala.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentDetailsBinding
import com.example.chickenmasala.databinding.FragmentIngredientsBinding

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val ingredients = it.getString("aa")
            binding.textIngredients.text = ingredients?.formatIngredients()
        }
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



