package org.example.project.domain.entity
data class Order(
    val id: Int,
    val buyerId: Int,
    val sellerId: Int,
    val listingId: Int,
    val status: String = "pending",
    val totalPrice: Double = 0.0,
    val createdAt: String?,
    val updatedAt: String?
)