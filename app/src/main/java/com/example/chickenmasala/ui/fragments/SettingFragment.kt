package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }
    private fun setClickListeners(){
        binding.textFavourites.setOnClickListener{
            navigateToFavoriteFragment()
        }
    }
    private fun navigateToFavoriteFragment(){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragment_container,
            FavouriteResultFragment(),
            FavouriteResultFragment.TAG_Favourite_Fragment
        )
        fragmentTransaction.commit()
    }
    companion object {
        const val TAG = "Settings Fragment Tag"
    }
}