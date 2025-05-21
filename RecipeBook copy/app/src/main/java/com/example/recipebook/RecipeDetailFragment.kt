package com.example.recipebook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipebook.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private var recipeName: String? = null
    private var recipeDescription: String? = null
    private var imageResId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeName = it.getString("name")
            recipeDescription = it.getString("description")
            imageResId = it.getInt("imageResId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRecipeName.text = recipeName
        binding.tvRecipeDescription.text = recipeDescription
        binding.ivRecipeImage.setImageResource(imageResId)

        // Dummy values for now – can be dynamic later
        binding.tvCalories.text = "300 Cal"
        binding.tvServings.text = "2 Servings"
        binding.tvTime.text = "20 mins"

        // Default tab = Ingredients
        showIngredients()

        binding.tabIngredients.setOnClickListener {
            updateTabSelection(true)
            showIngredients()
        }

        binding.tabDirections.setOnClickListener {
            updateTabSelection(false)
            showDirections()
        }
    }

    private fun updateTabSelection(isIngredientsSelected: Boolean) {
        if (isIngredientsSelected) {
            binding.tabIngredients.setBackgroundResource(com.example.recipebook.R.drawable.tab_background_selected)
            binding.tabDirections.setBackgroundResource(com.example.recipebook.R.drawable.tab_background_unselected)
        } else {
            binding.tabIngredients.setBackgroundResource(com.example.recipebook.R.drawable.tab_background_unselected)
            binding.tabDirections.setBackgroundResource(com.example.recipebook.R.drawable.tab_background_selected)
        }
    }

    private fun showIngredients() {
        val ingredients = listOf(
            "• 1 1/2 cups apple juice",
            "• 1 banana",
            "• 1 1/2 cups frozen mixed berries",
            "• 3/4 cup vanilla Greek yogurt",
            "• 1 tbsp honey (optional)",
            "• Fresh berries, mint sprigs (optional)"
        )
        binding.tvTabContent.text = ingredients.joinToString("\n")
    }

    private fun showDirections() {
        val directions = listOf(
            "• Add apple juice to blender.",
            "• Peel and slice the banana.",
            "• Add mixed berries and yogurt.",
            "• Blend until smooth.",
            "• Serve chilled with garnish."
        )
        binding.tvTabContent.text = directions.joinToString("\n")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}