package org.example.project.domain.entity

data class Payment(
    val id: Int,
    val orderId: Int,
    val userId: Int,
    val amount: Double,
    val paymentMethod: String,
    val status: String = "pending",
    val transactionId: String?,
    val createdAt: String?
)