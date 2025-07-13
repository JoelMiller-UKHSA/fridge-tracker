package com.example.fridgetracker

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fridgetracker.databinding.FragmentNewItemSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.util.Locale

class NewItemSheet(var contentItem: ContentItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewItemSheetBinding
    private val contentsViewModel : ContentsViewModel by viewModels {
        ContentItemModelFactory((requireActivity().application as FridgeTrackerApplication).repository)
    }
    private var expiryDate: LocalDate? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (contentItem != null){
            binding.contentTitle.text = "Edit Item"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(contentItem!!.name)
            if (contentItem!!.expiryDate() != null){
                expiryDate = contentItem!!.expiryDate()!!
                updateDateButtonText()
            }
        }
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
        val expiryDateString = if (expiryDate == null) null else ContentItem.dataFormatter.format(expiryDate)
        if (contentItem == null){
            val newContent = ContentItem(name, expiryDateString)
            contentsViewModel.addContentItem(newContent)
        } else {
            contentItem!!.name = name
            contentItem!!.expiryDateString = expiryDateString
            contentsViewModel.updateContentItem(contentItem!!)
        }
        binding.name.setText("")
        dismiss()
    }
}