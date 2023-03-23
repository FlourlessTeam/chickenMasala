package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import com.example.chickenmasala.databinding.FragmentInstructionsBinding


class InstructionsFragment :
    BaseFragment<FragmentInstructionsBinding>(FragmentInstructionsBinding::inflate) {


    private fun String.formatInstructions(): String {
        return this.lines().mapIndexed { index, line ->
            "${index + 1}. $line"
        }.joinToString(separator = "\n\n")
    }

    companion object {
        fun newInstance(ingredients: String) = InstructionsFragment().apply {
            arguments = Bundle().apply {
                putString("aa", ingredients)
            }
        }
    }
}