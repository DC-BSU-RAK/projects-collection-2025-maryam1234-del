package com.example.recipebook.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipebook.R
import com.example.recipebook.Recipe
import com.example.recipebook.RecipeAdapter
import com.example.recipebook.databinding.FragmentRecipeListBinding

class RecipeListFragment : Fragment() {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    private var category: String? = null

    companion object {
        private const val ARG_CATEGORY = "category"

        fun newInstance(category: String): RecipeListFragment {
            val fragment = RecipeListFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getString(ARG_CATEGORY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCategoryTitle.text = "$category Recipes"

        val recipeList = when (category) {
            "Breakfast" -> listOf(
                Recipe(1, "Pancakes with Berries", "Fluffy pancakes topped with fresh berries", R.drawable.pancakes_berries),
                Recipe(2, "Avocado Toast", "Toasted bread topped with smashed avocado", R.drawable.avocado_toast),
                Recipe(3, "Omelette & Toast", "Classic omelette served with toast", R.drawable.omelette_toast)
            )
            "Lunch" -> listOf(
                Recipe(4, "Chicken Curry", "Spicy Indian chicken curry", R.drawable.chicken_curry),
                Recipe(5, "Spaghetti", "Classic Italian pasta with tomato sauce", R.drawable.spaghetti),
                Recipe(6, "Caesar Salad", "Fresh romaine with creamy Caesar dressing", R.drawable.caesar_salad)
            )
            "Dinner" -> listOf(
                Recipe(7, "Grilled Salmon", "Salmon fillet served with steamed veggies", R.drawable.grilled_salmon),
                Recipe(8, "Steak with Veggies", "Juicy steak paired with sautéed vegetables", R.drawable.steak_veggies),
                Recipe(9, "Stuffed Bell Peppers", "Bell peppers filled with rice and meat", R.drawable.stuffed_peppers)
            )
            "Dessert" -> listOf(
                Recipe(10, "Lava Cake", "Molten chocolate cake with a gooey center", R.drawable.lava_cake),
                Recipe(11, "Nutella Waffles", "Crispy waffles topped with creamy Nutella.", R.drawable.nutella_waffles),
                Recipe(12, "Brownie Sundae", "Warm brownie with ice cream and toppings", R.drawable.brownie_sundae)
            )
            else -> emptyList()
        }

        // Start shimmer animation
        binding.shimmerContainer.startShimmer()

        // Simulate loading with delay (e.g., 2 seconds)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.shimmerContainer.stopShimmer()
            binding.shimmerContainer.visibility = View.GONE
            binding.rvRecipes.visibility = View.VISIBLE

            // Now load actual RecyclerView
            binding.rvRecipes.layoutManager = LinearLayoutManager(requireContext())
            binding.rvRecipes.adapter = RecipeAdapter(
                recipes = recipeList,
                onItemClick = { selectedRecipe ->
                    val bundle = Bundle().apply {
                        putString("name", selectedRecipe.name)
                        putString("description", selectedRecipe.description)
                        putInt("imageResId", selectedRecipe.imageResId)
                    }

                    val detailFragment = RecipeDetailFragment()
                    detailFragment.arguments = bundle

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentsFl, detailFragment)
                        .addToBackStack(null)
                        .commit()
                },
                showExtras = false
            )
        }, 2000) // 2000ms delay for shimmer
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}