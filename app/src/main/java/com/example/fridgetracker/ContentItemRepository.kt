package com.example.fridgetracker

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ContentItemRepository(private val contentItemDao: ContentItemDao) {
    val allContentItems: Flow<List<ContentItem>> = contentItemDao.allContentItems()

    @WorkerThread
    suspend fun insertContentItem(contentItem: ContentItem){
        contentItemDao.insertContentItem(contentItem)
    }

    @WorkerThread
    suspend fun updateContentItem(contentItem: ContentItem){
        contentItemDao.updateContentItem(contentItem)
    }
}