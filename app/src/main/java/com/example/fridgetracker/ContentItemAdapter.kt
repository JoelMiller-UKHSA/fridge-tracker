package com.example.fridgetracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.databinding.ContentItemCellBinding

class ContentItemAdapter(
    private val contentItems: List<ContentItem>,
    private val clickListener: ContentItemClickListener
): RecyclerView.Adapter<ContentItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ContentItemCellBinding.inflate(from, parent, false)
        return ContentItemViewHolder(parent.context, binding, clickListener)
    }

    override fun getItemCount(): Int = contentItems.size

    override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {
        holder.bindContentItem(contentItems[position])
    }
}