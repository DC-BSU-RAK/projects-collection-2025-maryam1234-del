package com.example.recipebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.recipebook.databinding.FragmentCategoriesBinding
import com.example.recipebook.fragments.RecipeListFragment

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        // Setup click listeners with zoom animation
        setupBannerClick(binding.cardBreakfast, "Breakfast")
        setupBannerClick(binding.cardLunch, "Lunch")
        setupBannerClick(binding.cardDinner, "Dinner")
        setupBannerClick(binding.cardDessert, "Dessert")

        return binding.root
    }

    private fun setupBannerClick(card: FrameLayout, categoryName: String) {
        val zoomAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_up)

        card.setOnClickListener {
            card.startAnimation(zoomAnim)
            openCategory(categoryName)
        }
    }

    private fun openCategory(category: String) {
        val fragment = RecipeListFragment.newInstance(category)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsFl, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
