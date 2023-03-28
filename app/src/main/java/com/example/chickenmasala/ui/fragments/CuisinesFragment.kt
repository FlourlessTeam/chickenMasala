package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentCuisineBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.interactors.GetAllCuisines
import com.example.chickenmasala.ui.adapters.CuisinesAdapter
import com.example.chickenmasala.ui.interfaces.AppbarFragment


class CuisinesFragment : AppbarFragment<FragmentCuisineBinding>(FragmentCuisineBinding::inflate) {
    private val dataManager by lazy { DataManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAppbarBackButton(binding.toolbarCuisine)
        val adapter = CuisinesAdapter(CuisinesAdapter.CuisineListener {
            SubcategoryFragment(it).startTransaction(requireActivity())
        })
        binding.recyclerCuisines.adapter = adapter
        val cuisines = arrayListOf<Cuisine>()
        GetAllCuisines(dataManager).execute().entries.forEach {
            cuisines.add(Cuisine(it.key, it.value))
        }
        adapter.submitList(cuisines)
    }

}


