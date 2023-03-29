package com.example.chickenmasala.ui.fragments.navigationbarfragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.entities.Cuisine
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.interactors.*
import com.example.chickenmasala.ui.adapters.homeadapters.*
import com.example.chickenmasala.ui.fragments.detailsscreenfragment.DetailsFragment
import com.example.chickenmasala.ui.fragments.SubcategoryFragment
import com.example.chickenmasala.ui.interfaces.HomeInteractionListener
import com.example.chickenmasala.ui.interfaces.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeInteractionListener {
    private val dataManager:RecipesDataSource by lazy { DataManager(requireContext()) }
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
        val recipe = GetRecipesLessThanGivenTime(dataManager).execute(10,20)
            submitList(recipe)

        }
    }
    private val easyRecipes by lazy {
        EasyRecipesAdapter(this).apply {
        val recipe = GetRecipesLessThanGivenIngredient(dataManager).execute(10,20)
            submitList(recipe)

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecyclerView.adapter= HomeAdapter().apply {
            submitList(listOf(cuisineAdapter,forYouAdapter,fastRecipes,easyRecipes))


        }
//        setupForYouRecipesAdapter()
//        setupUnder20MinsAdapter()
//        setupUnder5IngredientsAdapter()
//        setupCuisinesAdapter()
//        onClickCuisinesViewAll()
//        onClickForYouViewAll()
//        onClickText20MinSubViewAll()
//        onClickUnder5ingredientSubtitleViewAll()
    }

//    private fun setupCuisinesAdapter() {
//        val homeCuisines = GetRequiredHomeCuisines(dataManager).execute(10)
//        binding.cuisineRecyclerView.adapter = HomeCuisinesAdapter(this).apply {
//            submitList(homeCuisines)
//        }
//    }
//
//    private fun setupForYouRecipesAdapter() {
//        GetRandomRecipes(dataManager).execute(10)
//        binding.recyclerViewForYou.adapter =
//            ForYouRecipesAdapter(this).apply {
//                submitList(GetRandomRecipes(dataManager).execute(20))
//            }
//    }
//
//    private fun setupUnder20MinsAdapter() {
//        val recipes = GetRecipesLessThanGivenTime(dataManager).execute(20, 10)
//        binding.under20minRecyclerView.adapter =
//            Under20MinOrEqualRecipesAdapter(this).apply {
//                submitList(recipes)
//            }
//    }
//
//    private fun setupUnder5IngredientsAdapter() {
//        val recipes = GetRecipesLessThanGivenIngredient(dataManager).execute(5, 10)
//        binding.under5ingredientRecyclerView.adapter =
//            Under5IngredientOrEqualRecipesAdapter( this).apply {
//                submitList(recipes)
//            }
//    }
//
    override fun onCuisineClicked(cuisine: Cuisine) {
        SubcategoryFragment(cuisine).startTransaction(requireActivity())
    }

    override fun onRecipeClicked(recipe: Recipe) {
        DetailsFragment(recipe).startFragmentTransaction(requireActivity())

    }


//    private fun onClickCuisinesViewAll() {
//        binding.textCuisinesViewAll.setOnClickListener {
//            CuisinesFragment().startTransaction(requireActivity())
//        }
//    }
//
//    private fun onClickText20MinSubViewAll() {
//        binding.text20MinSub.setOnClickListener {
//            SubcategoryFragment(
//                Cuisine(
//                    "Under 20 min meal",
//                    GetRecipesLessThanGivenTime(dataManager).getFullRecipesLessThanGivenTimeList(20)
//                )
//            ).startTransaction(
//                requireActivity()
//            )
//        }
//    }
//
//    private fun onClickUnder5ingredientSubtitleViewAll() {
//        binding.under5ingredientSubtitle.setOnClickListener {
//            SubcategoryFragment(
//                Cuisine(
//                    "Under 5 ingredient meal",
//                    GetRecipesLessThanGivenIngredient(dataManager).getFullRecipesLessThanGivenIngredientList(
//                        5
//                    )
//                )
//            ).startTransaction(
//                requireActivity()
//            )
//        }
//    }
//
//    private fun onClickForYouViewAll() {
//        binding.ForYouViewAllSubtitle.setOnClickListener {
//            SubcategoryFragment(
//                Cuisine(
//                    "For You",
//                    dataManager.getAllRecipesData()
//                )
//            ).startTransaction(
//                requireActivity()
//            )
//        }
//    }


}