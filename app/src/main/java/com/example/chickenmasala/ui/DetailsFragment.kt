package com.example.chickenmasala.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)


        val adapter = PagerAdapter(requireFragmentManager())
        val viewPager = binding.viewPager
        val tabs = binding.tabs
        viewPager.adapter = adapter
        tabs.getTabAt(0)?.text = "Ingredients"
        tabs.getTabAt(1)?.text = "Instructions"
        tabs.setupWithViewPager(viewPager)
        return binding.root
    }
}

