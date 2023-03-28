package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.databinding.FragmentSettingBinding
import com.example.chickenmasala.ui.fragments.interfaces.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()

    }
    private fun setOnClickListeners(){
        binding.textHistory.setOnClickListener {
            AboutFragment().startTransaction(requireActivity())
        }
    }

}