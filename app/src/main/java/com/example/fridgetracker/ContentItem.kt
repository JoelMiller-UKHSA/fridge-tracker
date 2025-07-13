package com.example.fridgetracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "content_item_table")
class ContentItem(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "expiryDate") var expiryDateString: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) {
    fun expiryDate(): LocalDate? = if (expiryDateString == null) null
        else LocalDate.parse(expiryDateString, dataFormatter)

    companion object{
        val dataFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}