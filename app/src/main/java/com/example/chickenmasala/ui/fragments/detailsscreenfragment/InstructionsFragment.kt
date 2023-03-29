package com.example.chickenmasala.ui.fragments.detailsscreenfragment

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentInstructionsBinding
import com.example.chickenmasala.ui.adapters.detailsadapters.InstructionsAdapter
import com.example.chickenmasala.ui.interfaces.BaseFragment


class InstructionsFragment :
    BaseFragment<FragmentInstructionsBinding>(FragmentInstructionsBinding::inflate) {
    private lateinit var translatedInstructions: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            translatedInstructions = it.getStringArrayList(TAG)!!
        }
        val adapter = InstructionsAdapter(translatedInstructions)
        binding.recyclerViewInstructions.adapter = adapter
    }

    companion object {
        private const val TAG = "translatedInstructions"
        fun newInstance(translatedInstructions: List<String>) = InstructionsFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(TAG, ArrayList(translatedInstructions))
            }
        }
    }
}