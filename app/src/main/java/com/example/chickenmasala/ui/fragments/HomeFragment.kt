package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.entities.Recipe
import com.example.chickenmasala.ui.RecipeInteractionListener
import com.example.chickenmasala.ui.adapters.ForYouRecipesAdapter
import com.example.chickenmasala.ui.adapters.Under20MinRecipesAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    RecipeInteractionListener {

    private val dataManager by lazy { DataManager(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val randomForYouRecipes = dataManager.allRecipesData.shuffled()
        val under20MinRecipes = dataManager.allRecipesData.sortedBy { it.totalTimeInMins >= 20 }

        ///
        val forYouRecyclerViewAdapter = ForYouRecipesAdapter(randomForYouRecipes, this)
        val under20MinRecyclerViewAdapter = Under20MinRecipesAdapter(under20MinRecipes, this)

        ///
        binding.forYouRecyclerView.adapter = forYouRecyclerViewAdapter
        binding.under20MinRecyclerView.adapter = under20MinRecyclerViewAdapter
    }

    override fun onRecipeClicked(recipe: Recipe) {
        Toast.makeText(
            context,
            "Clicked on ${recipe.translatedRecipeName}",
            Toast.LENGTH_SHORT
        ).show()

    }

    /*
    private fun setupViews() {
        val chip1 = Chip(context, null, R.style.SingleChip_SelectedChip)
        chip1.isChecked = true
        chip1.text = "Indian"
        // Set the checked and unchecked styles for the Chip
        chip1.setChipDrawable(
            ChipDrawable.createFromResource(
                requireContext(),
                R.style.SingleChip_SelectedChip
            )
        )
        chip1.setChipDrawable(
            ChipDrawable.createFromResource(
                requireContext(),
                R.style.SingleChip_UnSelectedChip
            )
        )
        val chip2 = Chip(context, null, R.style.SingleChip_UnSelectedChip)
        chip2.text = "South Indian"

        binding.cuisinesChipGroup.apply {

            addView(chip1)
            addView(chip2)


        }
    }
*/

}



