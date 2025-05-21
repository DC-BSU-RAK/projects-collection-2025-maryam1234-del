package com.example.recipebook.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.recipebook.LoginActivity
import com.example.recipebook.R
import com.example.recipebook.databinding.FragmentProfileBinding
import com.example.recipebook.utils.PrefManager

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var prefManager: PrefManager

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            binding.profileImageView.setImageURI(it)
            prefManager.saveProfileImageUri(it.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefManager = PrefManager(requireContext())

        // Load saved data with correct method names
        binding.etUserName.setText(prefManager.getUserName() ?: "")
        binding.etEmail.setText(prefManager.getUserEmail() ?: "")
        prefManager.getProfileImageUri()?.let {
            binding.profileImageView.setImageURI(Uri.parse(it))
        }

        binding.profileImageView.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etUserName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                prefManager.saveUserName(name)
                prefManager.saveUserEmail(email)

                // Animate success message
                val successView = binding.tvSuccessMessage
                successView.visibility = View.VISIBLE
                successView.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))

                Handler(Looper.getMainLooper()).postDelayed({
                    successView.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out))
                    successView.visibility = View.GONE
                }, 1800)
            } else {
                Toast.makeText(requireContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogout.setOnClickListener {
            prefManager.clear()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}