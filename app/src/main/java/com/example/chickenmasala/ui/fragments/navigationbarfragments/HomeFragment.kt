package com.example.chickenmasala.ui.fragments.navigationbarfragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.*
import com.example.chickenmasala.ui.adapters.homeadapters.*
import com.example.chickenmasala.ui.fragments.SubcategoryFragment
import com.example.chickenmasala.ui.fragments.detailsscreenfragment.DetailsFragment
import com.example.chickenmasala.ui.interfaces.BaseFragment
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeInteractionListener {
    private val dataManager: RecipesDataSource by lazy { DataManager(requireContext()) }
    private val cuisineAdapter by lazy {
        HomeCuisinesAdapter(this).apply {
            val cuisines = GetRequiredHomeCuisines(dataManager).execute(20)
            submitList(cuisines)

        }
    }
    private val forYouAdapter by lazy {
        SpecialRecipesAdapter(this).apply {
            val recipe = GetRandomRecipes(dataManager).execute(20)
            submitList(recipe)

        }
    }
    private val fastRecipes by lazy {
        FastRecipesAdapter(this).apply {
            val recipe = GetRecipesLessThanGivenTime(dataManager).execute(10, 20)
            submitList(recipe)

        }
    }
    private val easyRecipes by lazy {
        EasyRecipesAdapter(this).apply {
            val recipe = GetRecipesLessThanGivenIngredient(dataManager).execute(10, 20)
            submitList(recipe)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecyclerView.adapter = HomeAdapter().apply {
            submitList(listOf(cuisineAdapter, forYouAdapter, fastRecipes, easyRecipes))
        }
    }
    override fun onCuisineClicked(cuisine: Cuisine) {
        SubcategoryFragment.newInstance(cuisine).startTransaction(requireActivity())
    }
    override fun onRecipeClicked(recipe: Recipe) {
        DetailsFragment.newInstance(recipe).startFragmentTransaction(requireActivity())

    }


}