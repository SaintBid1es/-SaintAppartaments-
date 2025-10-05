package org.example.project.data.model

data class Review(
    val id: Int,
    val reviewerId: Int,
    val reviewedUserId: Int,
    val rating: Int,
    val comment: String?,
    val listingId: Int?,
    val createdAt: String?
)