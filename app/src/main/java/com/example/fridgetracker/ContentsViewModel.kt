package com.example.fridgetracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.util.UUID

class ContentsViewModel: ViewModel() {
    var contentItems = MutableLiveData<MutableList<ContentItem>>()

    init {
        contentItems.value = mutableListOf()
    }

    fun addContentItem(newContent: ContentItem){
        val list = contentItems.value
        list!!.add(newContent)
        contentItems.postValue(list)
    }

    fun updateContentItem(id: UUID, name: String, expiryDate: LocalDate?){
        val list = contentItems.value
        val content = list!!.find{it.id == id}!!
        content.name = name
        content.expiryDate = expiryDate
        contentItems.postValue(list)
    }
}