package com.example.chickenmasala.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.chickenmasala.data.entities.Recipe
import com.example.chickenmasala.databinding.FragmentDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reciep = Recipe(
            translatedRecipeName = "Spicy Tomato Rice (Recipe)",
            translatedIngredients = "2 teaspoon cashew - or peanuts- 1/2 Teaspoon mustard- 1 dry red chilli- 1 teaspoon white urad dal- 1 teaspoon chickpea lentils- salt - as per taste- 1 green chilli- 1-1 / 2 tablespoon oil - 1/2 teaspoon asafoetida- 1/2 teaspoon cumin seeds- 3 teaspoons BC Belle Bhat powder-2-1 / 2 cups rice - cooked- 3 tomatoes",
            totalTimeInMins = 15,
            cuisine = "South Indian Recipes",
            translatedInstructions = "To make tomato puliogere- first cut the tomatoes.\n" +
                    "Now put in a mixer grinder and puree it.\n" +
                    "Now heat oil in a pan.\n" +
                    "After the oil is hot- add chana dal- urad dal- cashew and let it cook for 10 to 20 seconds.\n" +
                    "After 10 to 20 seconds- add cumin seeds- mustard seeds- green chillies- dry red chillies and curry leaves.\n" +
                    "After 30 seconds- add tomato puree to it and mix.\n" +
                    "Add BC Belle Bhat powder- salt and mix it.\n" +
                    "Allow to cook for 7 to 8 minutes and then turn off the gas.\n" +
                    "Take it out in a bowl- add cooked rice and mix it.\n" +
                    "Serve hot.\n" +
                    "Serve tomato puliogre with tomato cucumber raita and papad for dinner.",
            url = "https://www.archanaskitchen.com/spicy-tomato-rice-recipe-in-hindi",
            cleanedIngredients = "tomato-salt-chickpea lentils-green chilli-rice-mustard-bc belle bhat powder-dry red chilli-cashew peanuts-oilasafoetida-cumin seeds-white urad dal",
            imageUrl = "https://www.archanaskitchen.com/images/archanaskitchen/1-Author/b.yojana-gmail.com/Spicy_Thakkali_Rice_Tomato_Pulihora-1_edited.jpg",
            ingredientCount = 12
        )

        viewPagerSetup(reciep.cleanedIngredients, reciep.translatedInstructions)

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
                putSerializable("hh", recipe)
            }
        }
    }
}

