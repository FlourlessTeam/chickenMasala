package com.example.chickenmasala.ui.fragments.navigationbarfragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentSettingBinding
import com.example.chickenmasala.ui.MainActivity
import com.example.chickenmasala.ui.fragments.AboutFragment
import com.example.chickenmasala.ui.SharedState
import com.example.chickenmasala.ui.interfaces.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.switchTheme.isChecked = SharedState.isDarkTheme
        setOnClickListeners()


    }

    private fun setOnClickListeners() {
        setDarkModeClickListener()
        setOnHistoryClickedListener()
    }

    private fun setDarkModeClickListener() {
        binding.switchTheme.setOnCheckedChangeListener { _, _ ->
            if (isCurrentUiDarkTheme())
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            SharedState.isDarkTheme = !SharedState.isDarkTheme
            val mainActivity = (requireActivity() as MainActivity)
            mainActivity.binding.bottomNavigation.selectedItemId = R.id.navigation_settings


        }

    }

    private fun isCurrentUiDarkTheme() =
        AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

    private fun setOnHistoryClickedListener() {
        binding.textHistory.setOnClickListener {
            AboutFragment().startTransaction(requireActivity())
        }
    }


}