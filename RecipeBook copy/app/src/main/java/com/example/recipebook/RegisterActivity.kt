package com.example.recipebook

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recipebook.databinding.ActivityRegisterBinding
import com.example.recipebook.utils.PrefManager

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var prefs: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = PrefManager(this)

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save email and password separately
                prefs.saveUserEmail(email)
                prefs.saveUserPassword(password)

                Toast.makeText(this, "Registered Successfully! Please login.", Toast.LENGTH_SHORT).show()
                finish() // Close Register and return to Login
            }
        }
    }
}
