package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.chickenmasala.R
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.GetRandomRecipes
import com.example.chickenmasala.interactors.GetRecipesLessThanGivenTime
import com.example.chickenmasala.ui.RecipeInteractionListener
import com.example.chickenmasala.ui.adapters.ForYouRecipesAdapter
import com.example.chickenmasala.ui.adapters.Under20MinRecipesAdapter


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val dataManager by lazy { DataManager(requireContext()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupForYouRecipesAdapter()
        setupUnder20MinsAdapter()



    }

    private fun setupForYouRecipesAdapter() {
        val forYouRecipesList = GetRandomRecipes(dataManager).execute(10)
        val forYouRecipesInteractionListener = object : RecipeInteractionListener {
            override fun onRecipeClicked(recipe: Recipe) {
                DetailsFragment(recipe).startFragmentTransaction(requireActivity())
            }
        }
        binding.forYouRecyclerView.adapter =
            ForYouRecipesAdapter(forYouRecipesList, forYouRecipesInteractionListener)


    }
    private fun setupUnder20MinsAdapter() {
        val recipes = GetRecipesLessThanGivenTime(dataManager).execute(20, 10)
        val recipesInteractionListener = object : RecipeInteractionListener {
            override fun onRecipeClicked(recipe: Recipe) {
                DetailsFragment(recipe).startFragmentTransaction(requireActivity())
            }
        }
        binding.under20MinRecyclerView .adapter =
            Under20MinRecipesAdapter(recipes, recipesInteractionListener)


    }

    fun startFragmentTransaction(activity:FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, this, TAG)
        fragmentTransaction.commit()
    }


    companion object {
        const val TAG = "Home Fragment Tag"
    }

}



