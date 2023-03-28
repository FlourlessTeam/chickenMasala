package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.GetRandomRecipes
import com.example.chickenmasala.interactors.GetRecipesLessThanGivenIngredient
import com.example.chickenmasala.interactors.GetRecipesLessThanGivenTime
import com.example.chickenmasala.interactors.GetRequiredHomeCuisines
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener
import com.example.chickenmasala.ui.adapters.ForYouRecipesAdapter
import com.example.chickenmasala.ui.adapters.HomeCuisinesAdapter
import com.example.chickenmasala.ui.adapters.Under20MinOrEqualRecipesAdapter
import com.example.chickenmasala.ui.adapters.Under5IngredientOrEqualRecipesAdapter
import com.example.chickenmasala.ui.interfaces.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeInteractionListener {
    private val dataManager by lazy { DataManager(requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupForYouRecipesAdapter()
        setupUnder20MinsAdapter()
        setupUnder5IngredientsAdapter()
        setupCuisinesAdapter()
        onClickCuisinesViewAll()
        onClickForYouViewAll()
        onClickText20MinSubViewAll()
        onClickUnder5ingredientSubtitleViewAll()
    }

    private fun setupCuisinesAdapter() {
        val homeCuisines = GetRequiredHomeCuisines(dataManager).execute(10)
        binding.cuisineRecyclerView.adapter = HomeCuisinesAdapter(homeCuisines, this)
    }

    private fun setupForYouRecipesAdapter() {
        val forYouRecipesList = GetRandomRecipes(dataManager).execute(10)
        binding.recyclerViewForYou.adapter =
            ForYouRecipesAdapter(forYouRecipesList, this, dataManager)
    }

    private fun setupUnder20MinsAdapter() {
        val recipes = GetRecipesLessThanGivenTime(dataManager).execute(20, 10)
        binding.under20minRecyclerView.adapter =
            Under20MinOrEqualRecipesAdapter(recipes, this, dataManager)
    }

    private fun setupUnder5IngredientsAdapter() {
        val recipes = GetRecipesLessThanGivenIngredient(dataManager).execute(5, 10)
        binding.under5ingredientRecyclerView.adapter =
            Under5IngredientOrEqualRecipesAdapter(recipes, this, dataManager)
    }

    override fun onCuisineClicked(cuisine: Cuisine) {
        SubcategoryFragment(cuisine).startTransaction(requireActivity())
    }

    override fun onRecipeClicked(recipe: Recipe) {
        DetailsFragment(recipe).startFragmentTransaction(requireActivity())

    }


    private fun onClickCuisinesViewAll() {
        binding.textCuisinesViewAll.setOnClickListener {
            CuisinesFragment().startTransaction(requireActivity())
        }
    }

    private fun onClickText20MinSubViewAll() {
        binding.text20MinSub.setOnClickListener {
            SubcategoryFragment(
                Cuisine(
                    "Under 20 min meal",
                    GetRecipesLessThanGivenTime(dataManager).getFullRecipesLessThanGivenTimeList(20)
                )
            ).startTransaction(
                requireActivity()
            )
        }
    }

    private fun onClickUnder5ingredientSubtitleViewAll() {
        binding.under5ingredientSubtitle.setOnClickListener {
            SubcategoryFragment(
                Cuisine(
                    "Under 5 ingredient meal",
                    GetRecipesLessThanGivenIngredient(dataManager).getFullRecipesLessThanGivenIngredientList(
                        5
                    )
                )
            ).startTransaction(
                requireActivity()
            )
        }
    }

    private fun onClickForYouViewAll() {
        binding.ForYouViewAllSubtitle.setOnClickListener {
            SubcategoryFragment(
                Cuisine(
                    "For You",
                    dataManager.getAllRecipesData()
                )
            ).startTransaction(
                requireActivity()
            )
        }
    }



}