package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentAboutBinding
import com.example.chickenmasala.entities.AboutItem
import com.example.chickenmasala.entities.Chef
import com.example.chickenmasala.entities.History
import com.example.chickenmasala.entities.enums.AboutItemType
import com.example.chickenmasala.ui.adapters.AboutAdapter
import com.example.chickenmasala.ui.interfaces.AppbarFragment

class AboutFragment : AppbarFragment<FragmentAboutBinding>(FragmentAboutBinding::inflate){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        val chefs: List<Chef> =
            listOf(
                Chef(R.drawable.chef1, resources.getString(R.string.sanjeev)),
                Chef(R.drawable.chef2, resources.getString(R.string.vikas)),
                Chef(R.drawable.chef3, resources.getString(R.string.ranveer)),
                Chef(R.drawable.chef4, resources.getString(R.string.saransh)),
                Chef(R.drawable.chef5, resources.getString(R.string.madhur)),
            )

        val history: List<History> =
            listOf(
                History(resources.getString(R.string.history_text1), R.drawable.history_image1),
                History(resources.getString(R.string.history_text2), R.drawable.history_image2),
                History(resources.getString(R.string.history_text3), R.drawable.history_image3),
                History(resources.getString(R.string.history_text4), R.drawable.history_image4),
                History(resources.getString(R.string.history_text5), R.drawable.history_image5),
            )

        val itemList: MutableList<AboutItem<Any>> = mutableListOf()
        itemList.add(AboutItem(R.drawable.about_image, AboutItemType.TYPE_IMAGE))
        itemList.add(AboutItem(resources.getString(R.string.chefs), AboutItemType.TYPE_CHEF_TEXT))
        itemList.add(AboutItem(chefs, AboutItemType.TYPE_CHEFS))
        itemList.add(
            AboutItem(
                resources.getString(R.string.history),
                AboutItemType.TYPE_HISTORY_TEXT
            )
        )
        itemList.add(AboutItem(history, AboutItemType.TYPE_HISTORY))

        val adapter = AboutAdapter(itemList)
        binding.aboutRecyclerView.adapter = adapter
    }

}