package org.example.project.data.model

data class Report(
    val id: Int,
    val listingId: Int,
    val userId: Int,
    val reason: String,
    val createdAt: String?
)