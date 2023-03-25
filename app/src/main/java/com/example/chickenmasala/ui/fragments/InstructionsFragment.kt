package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentInstructionsBinding
import com.example.chickenmasala.ui.adapters.InstructionsAdapter


class InstructionsFragment(private val instructionsList: List<String>) :
    BaseFragment<FragmentInstructionsBinding>(FragmentInstructionsBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = InstructionsAdapter(instructionsList)
        binding.instructionRecyclerView.adapter = adapter
    }

}