package com.example.fridgetracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContentItem::class], version = 1, exportSchema = false)
abstract class ContentItemDatabase: RoomDatabase() {
    abstract fun contentItemDao(): ContentItemDao

    companion object{
        @Volatile
        private var INSTANCE: ContentItemDatabase? = null

        fun getDatabase(context: Context): ContentItemDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContentItemDatabase::class.java,
                    name = "content_item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}