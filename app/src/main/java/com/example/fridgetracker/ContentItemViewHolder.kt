package com.example.fridgetracker

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.databinding.ContentItemCellBinding

class ContentItemViewHolder(
    private val context: Context,
    private val binding: ContentItemCellBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bindContentItem(contentItem: ContentItem){
        binding.name.text = contentItem.name
    }
}