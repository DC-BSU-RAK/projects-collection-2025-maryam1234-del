package com.example.recipebook

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recipebook.databinding.ActivityMainBinding
import com.example.recipebook.CategoriesFragment
import com.example.recipebook.fragments.HomeFragment
import com.example.recipebook.fragments.ProfileFragment
import com.example.recipebook.SavedFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val categoriesFragment = CategoriesFragment()
    private val savedFragment = SavedFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle system window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Show default fragment and title
        setCurrentFragment(homeFragment)
        binding.toolbarTitleTv.text = "Home"

        // BottomNavigationView item selection listener
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_home -> {
                    setCurrentFragment(homeFragment)
                    binding.toolbarTitleTv.text = "Home"
                    true
                }
                R.id.item_categories -> {
                    setCurrentFragment(categoriesFragment)
                    binding.toolbarTitleTv.text = "Categories"
                    true
                }
                R.id.item_saved -> {
                    setCurrentFragment(savedFragment)
                    binding.toolbarTitleTv.text = "Saved"
                    true
                }
                R.id.item_profile -> {
                    setCurrentFragment(profileFragment)
                    binding.toolbarTitleTv.text = "Profile"
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsFl, fragment)
            .commit()
    }
}
