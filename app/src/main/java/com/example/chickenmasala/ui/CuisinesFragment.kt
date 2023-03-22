



package com.example.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentCuisineBinding
import com.example.chickenmasala.interactors.GetCuisineImageAndName

class CuisinesFragment : Fragment() {
    private val dataManager by lazy { DataManager(requireContext()) }
    private var _binding: FragmentCuisineBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCuisineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ContentTextView.text=GetCuisineImageAndName(dataManager).execute().toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


