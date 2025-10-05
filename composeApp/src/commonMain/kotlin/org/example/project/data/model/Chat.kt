package org.example.project.data.model

data class Chat(
    val id: Int,
    val buyerId: Int,
    val sellerId: Int,
    val listingId: Int,
    val createdAt: String?
)
