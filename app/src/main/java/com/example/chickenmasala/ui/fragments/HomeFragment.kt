package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.*
import com.example.chickenmasala.ui.HomeInteractionListener
import com.example.chickenmasala.ui.adapters.ForYouRecipesAdapter
import com.example.chickenmasala.ui.adapters.HomeCuisinesAdapter
import com.example.chickenmasala.ui.adapters.Under20MinOrEqualRecipesAdapter
import com.example.chickenmasala.ui.adapters.Under5IngredientOrEqualRecipesAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeInteractionListener {
    private val dataManager by lazy { DataManager(requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupForYouRecipesAdapter()
        setupUnder20MinsAdapter()
        setupUnder5IngredientsAdapter()
        setupCuisinesAdapter()
    }

    private fun setupCuisinesAdapter() {
        val homeCuisines = GetRequiredHomeCuisines(dataManager).execute()
        binding.cuisineRecyclerView.adapter = HomeCuisinesAdapter(homeCuisines, this)
    }

    private fun setupForYouRecipesAdapter() {
        val forYouRecipesList = GetRandomRecipes(dataManager).execute(10)
        binding.recyclerViewForYou.adapter = ForYouRecipesAdapter(forYouRecipesList, this)
    }

    private fun setupUnder20MinsAdapter() {
        val recipes = GetRecipesLessThanGivenTime(dataManager).execute(20, 10)
        binding.under20minRecyclerView.adapter = Under20MinOrEqualRecipesAdapter(recipes, this)
    }

    private fun setupUnder5IngredientsAdapter() {
        val recipes = GetRecipesLessThanGivenIngredient(dataManager).execute(5, 10)
        binding.under5ingredientRecyclerView.adapter =
            Under5IngredientOrEqualRecipesAdapter(recipes, this)
    }

    override fun onCuisineClicked(cuisine: Cuisine) {
        SubcategoryFragment(cuisine).startFragmentTransaction(requireActivity())
    }

    override fun onRecipeClicked(recipe: Recipe) {
        DetailsFragment(recipe).startFragmentTransaction(requireActivity())
    }


    fun startFragmentTransaction(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, this, TAG)
        fragmentTransaction.commit()
    }

    companion object {
        const val TAG = "Home Fragment Tag"
    }
}