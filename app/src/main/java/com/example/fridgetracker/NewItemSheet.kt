package com.example.fridgetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fridgetracker.databinding.FragmentNewItemSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewItemSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewItemSheetBinding
    private lateinit var contentsViewModel: ContentsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        contentsViewModel = ViewModelProvider(activity)[ContentsViewModel::class.java]
        binding.addItemButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewItemSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction(){
        contentsViewModel.name.value = binding.name.text.toString()
        binding.name.setText("")
        dismiss()
    }
}