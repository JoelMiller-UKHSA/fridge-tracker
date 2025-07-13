package com.example.fridgetracker

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import com.example.fridgetracker.databinding.FragmentNewItemSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.util.Locale

class NewItemSheet(var contentItem: ContentItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewItemSheetBinding
    private lateinit var contentsViewModel: ContentsViewModel
    private var expiryDate: LocalDate? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (contentItem != null){
            binding.contentTitle.text = "Edit Item"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(contentItem!!.name)
            if (contentItem!!.expiryDate != null){
                expiryDate = contentItem!!.expiryDate!!
                updateDateButtonText()
            }
        }

        contentsViewModel = ViewModelProvider(activity)[ContentsViewModel::class.java]
        binding.addItemButton.setOnClickListener {
            saveAction()
        }
        binding.expiryDateButton.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        val activity = requireActivity()
        if (expiryDate == null)
            expiryDate = LocalDate.now()
        val listener = DatePickerDialog.OnDateSetListener{_, selectedYear, selectedMonth, selectedDay ->
            expiryDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
            updateDateButtonText()
        }
        val dialog = DatePickerDialog(activity, listener, expiryDate!!.year, expiryDate!!.monthValue - 1, expiryDate!!.dayOfMonth)
        dialog.setTitle("Expiry Date")
        dialog.show()
    }

    private fun updateDateButtonText() {
        binding.expiryDateButton.text = String.format(Locale.UK,"%02d %s", expiryDate!!.dayOfMonth, expiryDate!!.month.name)
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
            val newContent = ContentItem(name, expiryDate)
            contentsViewModel.addContentItem(newContent)
        } else {
            contentsViewModel.updateContentItem(contentItem!!.id, name, expiryDate)
        }
        binding.name.setText("")
        dismiss()
    }
}