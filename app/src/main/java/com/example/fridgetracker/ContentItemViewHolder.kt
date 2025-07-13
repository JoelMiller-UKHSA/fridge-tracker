package com.example.fridgetracker

import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.databinding.ContentItemCellBinding
import java.time.format.DateTimeFormatter

class ContentItemViewHolder(
    private val binding: ContentItemCellBinding
): RecyclerView.ViewHolder(binding.root) {
    private val dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy")
    fun bindContentItem(contentItem: ContentItem){
        binding.name.text = contentItem.name
        binding.expiryTime.text = dateFormat.format(contentItem.expiryDate)
    }
}