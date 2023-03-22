package com.example.chickenmasala.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {
    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        arguments.let {
            val ingredients = it?.getString("hh")
//            binding.textView1.text = ingredients
        }
        binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        arguments?.let {
            val instructions = it.getString("aa")
            binding.textView1.text = instructions?.formatInstructions()
        }
    }

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