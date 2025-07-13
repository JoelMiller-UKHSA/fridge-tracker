package com.example.fridgetracker

import android.app.Application

class FridgeTrackerApplication: Application() {
    private val database by lazy { ContentItemDatabase.getDatabase(this) }
    val repository by lazy { ContentItemRepository(database.contentItemDao()) }
}