package com.example.fridgetracker

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fridgetracker.databinding.FragmentNewItemSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewItemSheet(var contentItem: ContentItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewItemSheetBinding
    private lateinit var contentsViewModel: ContentsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (contentItem != null){
            binding.contentTitle.text = "Edit Item"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(contentItem!!.name)
        }

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
        val name = binding.name.text.toString()

        if (contentItem == null){
            val newContent = ContentItem(name, null)
            contentsViewModel.addContentItem(newContent)
        } else {
            contentsViewModel.updateContentItem(contentItem!!.id, name, null)
        }
        binding.name.setText("")
        dismiss()
    }
}