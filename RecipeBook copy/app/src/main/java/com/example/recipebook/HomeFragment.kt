package com.example.recipebook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipebook.R
import com.example.recipebook.Recipe
import com.example.recipebook.RecipeAdapter
import com.example.recipebook.SavedRecipes
import com.example.recipebook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val sampleRecipes = listOf(
        Recipe(1, "Spaghetti Carbonara", "Classic Italian pasta dish", R.drawable.spaghetti),
        Recipe(2, "Chicken Curry", "Spicy and creamy curry", R.drawable.chicken_curry),
        Recipe(3, "Avocado Toast", "Healthy breakfast option", R.drawable.avocado_toast),
        Recipe(4, "Biryani", "Delicious Indian spiced rice dish", R.drawable.biryani),
        Recipe(5, "Pizza", "Fluffy fun pizza", R.drawable.pizza)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (sampleRecipes.size % 2 != 0 && position == sampleRecipes.size - 1) 2 else 1
            }
        }

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = RecipeAdapter(
            recipes = sampleRecipes,
            onItemClick = { recipe ->
                SavedRecipes.addRecipe(recipe)
                Toast.makeText(requireContext(), "Saved: ${recipe.name}", Toast.LENGTH_SHORT).show()
            },
            showExtras = false // ❌ Don't show heart/rating in Home
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
