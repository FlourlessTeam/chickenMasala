package com.example.chickenmasala.ui.interfaces

import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.example.chickenmasala.R

abstract class AppbarFragment<T : ViewBinding>(bindingInflater: (layoutInflater: LayoutInflater) -> T) :
    BaseFragment<T>(bindingInflater) {

    fun startTransaction(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, this).addToBackStack(null)
            .commit()
    }
    protected fun setUpAppbarBackButton(view: Toolbar) {
        view.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
