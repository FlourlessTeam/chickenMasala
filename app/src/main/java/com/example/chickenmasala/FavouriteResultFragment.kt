package com.example.chickenmasala

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FavouriteResultBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.GetFavoritesRecipes
import com.google.android.material.imageview.ShapeableImageView

class FavouriteResultFragment: Fragment() {

    lateinit var binding: FavouriteResultBinding
    private val dataSource = DataManager(requireContext())
    private val getFavoritesRecipes = GetFavoritesRecipes(dataSource)
    private val favouriteRecipes = getFavoritesRecipes.execute()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavouriteResultBinding.inflate(inflater,container,false)
//        binding.tvFavourite.text = favouriteRecipes.map { it.translatedRecipeName }.toString()
//        binding.tvFavourite.invalidate()
        return binding.root
    }


}