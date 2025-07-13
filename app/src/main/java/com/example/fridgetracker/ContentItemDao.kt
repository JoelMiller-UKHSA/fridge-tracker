package com.example.fridgetracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentItemDao {
    @Query("SELECT * FROM content_item_table ORDER BY expiryDate ASC")
    fun allContentItems(): Flow<List<ContentItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContentItem(contentItem: ContentItem)

    @Update
    suspend fun updateContentItem(contentItem: ContentItem)
}