package com.example.chickenmasala.ui.fragments

import android.os.Bundle
import android.view.View
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.ui.adapters.ForYouRecipesAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val dataManager by lazy { DataManager(requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ForYouRecipesAdapter(dataManager.allRecipesData)
        binding.forYouRecyclerView.adapter = adapter
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



