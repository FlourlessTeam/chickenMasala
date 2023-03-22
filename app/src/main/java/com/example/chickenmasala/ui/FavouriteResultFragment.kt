package com.example.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FavouriteResultBinding
import com.example.chickenmasala.interactors.GetFavoritesRecipes

class FavouriteResultFragment: Fragment() {

    private lateinit var binding: FavouriteResultBinding
    private val dataSource = DataManager(requireContext())
    private val getFavoritesRecipes = GetFavoritesRecipes(dataSource)
    private val favouriteRecipes = getFavoritesRecipes.execute()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavouriteResultBinding.inflate(inflater,container,false)
        return binding.root
    }


}