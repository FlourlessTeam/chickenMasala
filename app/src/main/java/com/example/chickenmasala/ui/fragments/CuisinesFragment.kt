package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentCuisineBinding
import com.example.chickenmasala.interactors.GetCuisineImageAndName

class CuisinesFragment : BaseFragment<FragmentCuisineBinding>(FragmentCuisineBinding::inflate) {
    private val dataManager by lazy { DataManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ContentTextView.text = GetCuisineImageAndName(dataManager).execute().toString()
    }



}


