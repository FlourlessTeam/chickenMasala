package com.example.chickenmasala

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.chickenmasala.databinding.FavouriteResultBinding

class FavouriteResultFragment: Fragment() {

    lateinit var binding: FavouriteResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavouriteResultBinding.inflate(inflater,container,false)
        val itemView = layoutInflater.inflate(R.layout.favourite_content, binding.listContainer, false ) as ConstraintLayout
        val emptyView = layoutInflater.inflate(R.layout.empty_favourite_screen, binding.listContainer, false ) as ConstraintLayout
        return binding.root
    }
}