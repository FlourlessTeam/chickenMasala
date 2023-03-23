package com.example.chickenmasala.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.chickenmasala.databinding.FragmentDetailsBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reciep = Recipe(
            translatedRecipeName = "Spicy Tomato Rice (Recipe)",
            translatedIngredients = listOf("suger","suger"),
            totalTimeInMins = 15,
            cuisine = "South Indian Recipes",
            translatedInstructions = listOf("1suger","tea"),
            url = "https://www.archanaskitchen.com/spicy-tomato-rice-recipe-in-hindi",
            cleanedIngredients = listOf("tea","tea"),
            imageUrl = "https://www.archanaskitchen.com/images/archanaskitchen/1-Author/b.yojana-gmail.com/Spicy_Thakkali_Rice_Tomato_Pulihora-1_edited.jpg",
            ingredientCount = 12
        )

        viewPagerSetup(reciep.cleanedIngredients.toString(), reciep.translatedInstructions.toString())

        updateViews(reciep)
        showMoreInfoCallback(reciep.url)


    }


    private fun viewPagerSetup(ingredients: String, instructions: String) {
        val adapter = PagerAdapter(
            childFragmentManager,
            lifecycle,
            ingredients,
            instructions
        )
        binding.apply {
            viewPager.adapter = adapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Ingredients"
                    else -> tab.text = "Instructions"
                }
            }.attach()
        }
    }

    private fun updateViews(reciep: Recipe) {
        updateImage(reciep.imageUrl)
        binding.textMealTime.text = reciep.totalTimeInMins.toString()
        binding.textMealIngredients.text = reciep.ingredientCount.toString()
        binding.textView2.text = reciep.translatedRecipeName

    }

    private fun showMoreInfoCallback(url: String) {
        binding.moreInfo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun updateImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(binding.mainImage)
    }

    companion object {
        fun newInstance(recipe: Recipe) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("hh", recipe)
            }
        }
    }
}

