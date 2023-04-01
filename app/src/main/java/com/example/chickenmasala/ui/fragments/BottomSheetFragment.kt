package com.example.chickenmasala.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.forEach
import com.example.chickenmasala.R
import com.example.chickenmasala.ui.interfaces.BottomSheetListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class BottomSheetFragment(val bottomSheetListener: BottomSheetListener) : BottomSheetDialogFragment() {


    lateinit var ingredientChips: ChipGroup
    lateinit var cookingTimeChips: ChipGroup
    lateinit var showResult: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        init(view)



        showResult.setOnClickListener {
           buttonClicked()
        }
        return view
    }
    private fun init(view: View) {
        ingredientChips = view.findViewById<ChipGroup>(R.id.ingredientchipgroup)
        cookingTimeChips = view.findViewById<ChipGroup>(R.id.timechipgroup)
        showResult = view.findViewById<Button>(R.id.show)
    }
    private fun buttonClicked() {



        val time = when(cookingTimeChips.checkedChipId){
            R.id.fivemin -> 5
            R.id.tenmin -> 10
            R.id.fifteenmin -> 15
            R.id.twentymin -> 20
            else -> 0
        }
        val ing = when(ingredientChips.checkedChipId){
            R.id.fiveing-> 5
            R.id.tening -> 10
            R.id.fifteening -> 15
            R.id.twentying -> 20
            else -> 0
        }


        //bottomSheetListener.onButtonClicked(time , ing)
        //dismiss()
    }

}