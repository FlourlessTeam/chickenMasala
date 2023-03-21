package com.example.chickenmasala.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.databinding.FragmentCuisinesBinding
import com.example.chickenmasala.databinding.ItemCuisineBinding

private const val ARG_CUISINES = "cuisines"

class CuisinesFragment : Fragment() {
    private var _binding: FragmentCuisinesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCuisinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cuisines = arguments?.getStringArrayList(ARG_CUISINES)
        cuisines?.forEach {
            addCuisine(it)
        }
    }

    private fun addCuisine(cuisineName: String) {
        val view = ItemCuisineBinding.inflate(layoutInflater, null, false)
        val params = ViewGroup.MarginLayoutParams(480, 480)
        params.topMargin = 8
        params.bottomMargin = 8
        if (binding.gridLayout.childCount % 2 == 0) {
            params.marginEnd = 48
        }
        params.topMargin = 32
        view.cuisineNameTextView.text = cuisineName
        view.root.layoutParams = params
        view.root.setOnClickListener { onCuisineClick(cuisineName) }
        binding.gridLayout.addView(view.root)
    }

    private fun onCuisineClick(cuisineName: String) {
        Log.d("Cuisines", "onCuisineClick: $cuisineName")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(cuisines: ArrayList<String>) = CuisinesFragment().apply {
            arguments = Bundle().apply { putStringArrayList(ARG_CUISINES, cuisines) }
        }
    }

}