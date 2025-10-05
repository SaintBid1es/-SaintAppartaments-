package org.example.project.domain.entity

data class OrderItem(
    val id: Int,
    val orderId: Int,
    val listingId: Int,
    val price: Double
)