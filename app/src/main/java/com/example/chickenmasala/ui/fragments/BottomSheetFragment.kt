package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentBottomSheetBinding
import com.example.chickenmasala.ui.interfaces.BottomSheetListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment(private val bottomSheetListener: BottomSheetListener) :
    BottomSheetDialogFragment() {


    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonShow.setOnClickListener {
            buttonClicked()
        }

    }

    private fun buttonClicked() {
        val selectedTime = when (binding.chipGroupTime.checkedChipId) {
            binding.chipFiveMins.id -> 5
            binding.chipTenMins.id -> 10
            binding.chipFifteenMins.id -> 15
            binding.chipTwentyMins.id -> 20
            else -> Int.MAX_VALUE
        }
        val selectedIngredient = when (binding.chipGroupIngredients.checkedChipId) {
            binding.chipFiveIngredients.id -> 5
            binding.chipTenIngredients.id -> 10
            binding.chipFifteenIngredients.id -> 15
            binding.chipTwentyIngredients.id -> 20
            else -> Int.MAX_VALUE
        }
        bottomSheetListener.onButtonClicked(selectedTime, selectedIngredient)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}