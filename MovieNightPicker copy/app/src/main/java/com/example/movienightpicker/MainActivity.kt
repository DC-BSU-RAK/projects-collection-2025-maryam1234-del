package com.example.movienightpicker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.movienightpicker.R

class MainActivity : AppCompatActivity() {

    // Declare variables
    private lateinit var moodSpinner: Spinner
    private lateinit var genreSpinner: Spinner
    private lateinit var companionSpinner: Spinner
    private lateinit var calculateButton: Button
    private lateinit var infoButton: Button
    private lateinit var resultCard: CardView
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        moodSpinner = findViewById(R.id.moodSpinner)
        genreSpinner = findViewById(R.id.genreSpinner)
        companionSpinner = findViewById(R.id.companionSpinner)
        calculateButton = findViewById(R.id.calculateButton)
        infoButton = findViewById(R.id.infoButton)
        resultCard = findViewById(R.id.resultCard)
        resultText = findViewById(R.id.resultText)

        // Setup spinners
        setupSpinners()

        // Set button click listeners
        calculateButton.setOnClickListener {
            showMovieSuggestion()
        }

        infoButton.setOnClickListener {
            showHowItWorksPopup()  // Show the full-screen instructions pop-up
        }
    }

    private fun showHowItWorksPopup() {
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.popup_layout, null)

        // Create and configure the AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // Handle the close button inside the pop-up
        val closeButton: Button = dialogView.findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            alertDialog.dismiss()  // Close the pop-up when the button is clicked
        }

        alertDialog.show()
    }

    private fun setupSpinners() {
        // Mood Spinner
        val moods = listOf("Select Mood", "Happy", "Sad", "Excited", "Romantic", "Adventurous")
        val moodAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, moods)
        moodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        moodSpinner.adapter = moodAdapter

        // Genre Spinner
        val genres = listOf("Select Genre", "Action", "Comedy", "Drama", "Horror", "Sci-Fi", "Romantic")
        val genreAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genreSpinner.adapter = genreAdapter

        // Companion Spinner
        val companions = listOf("Select Companion", "Alone", "Friends", "Family", "Partner")
        val companionAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, companions)
        companionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        companionSpinner.adapter = companionAdapter
    }

    private fun showMovieSuggestion() {
        val mood = moodSpinner.selectedItem.toString()
        val genre = genreSpinner.selectedItem.toString()
        val companion = companionSpinner.selectedItem.toString()

        if (mood.startsWith("Select") || genre.startsWith("Select") || companion.startsWith("Select")) {
            Toast.makeText(this, "Please select all options!", Toast.LENGTH_SHORT).show()
            return
        }

        // Simple recommendation logic
        val suggestion = when {
            mood == "Happy" && genre == "Comedy" -> "🎬 Watch: The Hangover"
            mood == "Sad" && genre == "Drama" -> "🎬 Watch: The Pursuit of Happyness"
            mood == "Excited" && genre == "Action" -> "🎬 Watch: Mad Max: Fury Road"
            mood == "Romantic" && genre == "Romantic" -> "🎬 Watch: The Notebook"
            genre == "Horror" -> "🎬 Watch: The Conjuring"
            genre == "Sci-Fi" -> "🎬 Watch: Interstellar"
            else -> "🎬 Watch: Forrest Gump (Always a Good Choice!)"
        }

        // Show result
        resultCard.visibility = View.VISIBLE
        resultText.text = suggestion
    }
}
