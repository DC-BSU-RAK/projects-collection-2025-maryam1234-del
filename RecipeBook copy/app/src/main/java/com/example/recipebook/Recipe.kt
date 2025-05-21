package com.example.recipebook

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: Int,
    val rating: Float = 4.5f
)
