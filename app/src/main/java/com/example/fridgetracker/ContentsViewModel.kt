package com.example.fridgetracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContentsViewModel(private val repository: ContentItemRepository): ViewModel() {
    var contentItems: LiveData<List<ContentItem>> = repository.allContentItems.asLiveData()

    fun addContentItem(newContent: ContentItem) = viewModelScope.launch {
        repository.insertContentItem(newContent)
    }
    fun updateContentItem(contentItem: ContentItem) = viewModelScope.launch {
        repository.updateContentItem(contentItem)
    }
}

class ContentItemModelFactory(private val repository: ContentItemRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContentsViewModel::class.java))
            return ContentsViewModel(repository) as T

        throw IllegalArgumentException("Unknown Class for View Model")
    }
}