package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentCuisineBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.interactors.GetAllCuisines
import com.example.chickenmasala.ui.adapters.CuisinesAdapter

class CuisinesFragment : BaseFragment<FragmentCuisineBinding>(FragmentCuisineBinding::inflate) {
    private val dataManager by lazy { DataManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CuisinesAdapter(CuisinesAdapter.CuisineListener {
            SubcategoryFragment(it).startFragmentTransaction(requireActivity())
        })
        binding.recyclerCuisines.adapter = adapter
        val cuisines = arrayListOf<Cuisine>()
        GetAllCuisines(dataManager).execute().entries.forEach {
            cuisines.add(Cuisine(it.key, it.value))
        }
        adapter.submitList(cuisines)
    }

    fun startFragmentTransaction(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, this, TAG)
        fragmentTransaction.commit()
    }

    companion object {
        const val TAG = "Subcategory Fragment Tag"
    }

}


