package com.faztbit.reignapp.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.faztbit.reignapp.databinding.DialogGeneralErrorBinding

class GeneralErrorDialog : DialogFragment() {
    private val args: GeneralErrorDialogArgs by navArgs()
    private lateinit var binding: DialogGeneralErrorBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogGeneralErrorBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        args.message?.let {
            binding.textViewTitle.text = it
        }
        setUpListener()
        return builder.create()
    }

    private fun setUpListener() {
        binding.buttonBack.setOnClickListener {
            dismiss()
        }
    }

}