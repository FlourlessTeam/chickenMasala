package com.example.chickenmasala

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chickenmasala.databinding.SearchResultBinding

class SearchResultFragment : Fragment() {

    lateinit var binding: SearchResultBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchResultBinding.inflate(inflater, container, false)
        val itemView= layoutInflater.inflate(R.layout.search_content, binding.listContainer, false) as ConstraintLayout
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return binding.root
    }

}