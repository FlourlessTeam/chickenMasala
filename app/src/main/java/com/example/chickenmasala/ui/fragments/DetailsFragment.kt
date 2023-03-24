package com.example.chickenmasala.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentDetailsBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.GetAllRecipes
import com.example.chickenmasala.ui.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val dataManager by lazy { DataManager(requireContext()) }
    private val getAllRecipes by lazy { GetAllRecipes(dataManager) }

    private val tabTitles = listOf("Ingredients", "Instructions")
    private lateinit var recipe: Recipe

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val recipeName = arguments?.getString(IngredientsFragment.RECIPE_NAME_KEY)
        val recipeName = "Maharashtrian Style Sheng Sola Recipe (Vegetable Stew)"

        recipe = getAllRecipes.execute().find { it.translatedRecipeName == recipeName }!!

        val fragmentList = listOf(
            IngredientsFragment.newInstance(recipeName),
            InstructionsFragment.newInstance(recipeName)
        )

        initViewPager(fragmentList)
        initTabLayout()

        updateViews(recipe)
        showMoreInfoCallback(recipe.url)
        favouriteCallBack()

    }


    private fun initViewPager(fragmentList: List<Fragment>) {
        val adapter = PagerAdapter(this, fragmentList)
        binding.viewPager.adapter = adapter
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun updateViews(recipe: Recipe) {
        updateImage(recipe.imageUrl)

        binding.apply {
            mealTimeText.text = recipe.totalTimeInMins.toString().plus(" Min")
            mealIngredientsText.text = recipe.ingredientCount.toString()
            mealNameText.text = recipe.translatedRecipeName
        }
    }

    private fun updateImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(binding.mainImage)
    }

    private fun showMoreInfoCallback(url: String) {
        binding.moreInfo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun favouriteCallBack() {
        binding.favourite.setOnClickListener {
            recipe.isFavourite = !recipe.isFavourite

            (it as ImageView).setImageResource(if (recipe.isFavourite) R.drawable.favorite_icon_filled else R.drawable.favorite_icon)
        }
    }

    companion object {
        const val RECIPE_NAME_KEY = "recipe_name"
        fun newInstance(recipeName: String) = IngredientsFragment().apply {
            arguments = Bundle().apply {
                putString(RECIPE_NAME_KEY, recipeName)
            }
        }
    }

}

