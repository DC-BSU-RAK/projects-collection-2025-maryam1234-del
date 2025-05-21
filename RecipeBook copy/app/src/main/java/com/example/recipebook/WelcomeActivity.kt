package com.example.recipebook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.recipebook.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🎥 Setup background video
        val videoPath = "android.resource://${packageName}/${R.raw.login_bg}"
        val uri = Uri.parse(videoPath)
        binding.videoView.setVideoURI(uri)
        binding.videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            binding.videoView.start()
        }

        // 🟡 "How it works" pop-up
        binding.btnHowItWorks.setOnClickListener {
            showHowItWorksDialog()
        }

        // 🟢 "Let's go" to Login
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun showHowItWorksDialog() {
        AlertDialog.Builder(this)
            .setTitle("How It Works")
            .setMessage(
                """
                • Browse delicious recipes by category.
                • Save your favorites to view later.
                • Edit your profile and change your avatar.
                • All data is stored locally on your device.
                
                Enjoy cooking with RecipeBook!
                """.trimIndent()
            )
            .setPositiveButton("Got it!") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        binding.videoView.start()
    }

    override fun onPause() {
        super.onPause()
        binding.videoView.pause()
    }
}
