package com.example.chickenmasala.ui.fragments.detailsscreenfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentDetailsBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.adapters.detailsadapters.PagerAdapter
import com.example.chickenmasala.ui.interfaces.AppbarFragment
import com.google.android.material.tabs.TabLayoutMediator

@Suppress("DEPRECATION")
class DetailsFragment :
    AppbarFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    private lateinit var recipe: Recipe
    private val tabTitles = listOf("Ingredients", "Instructions")
    private val fragmentList by lazy {
        listOf(
            IngredientsFragment.newInstance(recipe.translatedIngredients),
            InstructionsFragment.newInstance(recipe.translatedIngredients)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            recipe = it?.getParcelable(TAG)!!
        }
            setUpAppbarBackButton(binding.toolbarDetails)
            initViewPager(fragmentList)
            initTabLayout()
            updateViews(recipe)
            showMoreInfoCallback(recipe.url)
            favouriteCallBack()
            updateFavouriteIcon()

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
            textMealTime.text = recipe.totalTimeInMins.toString().plus(" Min")
            textMealIngredients.text = recipe.ingredientCount.toString()
            textMealName.text = recipe.translatedRecipeName
        }
    }

    private fun updateImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(binding.imageFood)
    }

    private fun showMoreInfoCallback(url: String) {
        binding.moreInfo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun favouriteCallBack() {
        binding.imageFavourite.setOnClickListener {
            recipe.isFavourite = !recipe.isFavourite
            updateFavouriteIcon()
        }
    }

    private fun updateFavouriteIcon() {
        binding.imageFavourite.setImageResource(if (recipe.isFavourite) R.drawable.favourite else R.drawable.favourite_outline)
    }

    fun startFragmentTransaction(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, this, TAG).addToBackStack(null)
            .commit()
    }

    companion object {
        const val TAG = "Details Fragment Tag"
        fun newInstance(recipe: Recipe) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TAG, recipe)
            }
        }
    }

}

