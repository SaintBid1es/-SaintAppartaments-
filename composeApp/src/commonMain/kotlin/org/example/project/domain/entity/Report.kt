package org.example.project.domain.entity

data class Report(
    val id: Int,
    val listingId: Int,
    val userId: Int,
    val reason: String,
    val createdAt: String?
)