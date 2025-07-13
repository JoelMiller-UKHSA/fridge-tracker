package com.example.fridgetracker

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.databinding.ContentItemCellBinding
import java.time.format.DateTimeFormatter

class ContentItemViewHolder(
    // context required for ContentItemAdapter
    private val context: Context,
    private val binding: ContentItemCellBinding,
    private val clickListener: ContentItemClickListener
): RecyclerView.ViewHolder(binding.root) {
    private val dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy")
    fun bindContentItem(contentItem: ContentItem){
        binding.name.text = contentItem.name
        binding.expiryTime.text = dateFormat.format(contentItem.expiryDate())
        binding.contentCellContainer.setOnClickListener{
            clickListener.editContentItem(contentItem)
        }
    }
}