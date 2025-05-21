package com.example.recipebook

object SavedRecipes {
    val savedList = mutableListOf<Recipe>()

    fun addRecipe(recipe: Recipe) {
        if (savedList.none { it.id == recipe.id }) {
            savedList.add(recipe)
        }
    }
}
