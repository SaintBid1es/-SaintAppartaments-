package org.example.project.domain.entity

data class Chat(
    val id: Int,
    val buyerId: Int,
    val sellerId: Int,
    val listingId: Int,
    val createdAt: String?
)
