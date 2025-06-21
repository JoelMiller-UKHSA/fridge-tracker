package com.example.fridgetracker

import java.time.LocalDate
import java.util.UUID

class ContentItem(
    var name: String,
    var expiryDate: LocalDate?,
    var id: UUID = UUID.randomUUID()
) {
}