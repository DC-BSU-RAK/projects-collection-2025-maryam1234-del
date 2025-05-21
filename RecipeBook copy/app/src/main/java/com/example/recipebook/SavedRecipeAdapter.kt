package com.example.recipebook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebook.databinding.ItemSavedRecipeBinding

class SavedRecipeAdapter(
    private val recipes: List<Recipe>
) : RecyclerView.Adapter<SavedRecipeAdapter.SavedViewHolder>() {

    inner class SavedViewHolder(private val binding: ItemSavedRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.tvRecipeTitle.text = recipe.name
            binding.tvRating.text = "4.5" // You can later make this dynamic
            binding.ivRecipeImage.setImageResource(recipe.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val binding = ItemSavedRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount() = recipes.size
}
