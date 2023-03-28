package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentAboutBinding
import com.example.chickenmasala.ui.interfaces.AppbarFragment

class AboutFragment : AppbarFragment<FragmentAboutBinding>(FragmentAboutBinding::inflate){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAppbarBackButton(binding.toolbarAbout)
    }
}