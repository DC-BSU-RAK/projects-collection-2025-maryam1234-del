package com.example.recipebook

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.recipebook.databinding.DialogHowItWorksBinding

class HowItWorksDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogHowItWorksBinding.inflate(LayoutInflater.from(context))

        val dialog = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setView(binding.root)
            .create()

        // Close icon
        binding.btnCloseIcon.setOnClickListener {
            dialog.dismiss()
        }

        // Optional close button
        binding.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}

