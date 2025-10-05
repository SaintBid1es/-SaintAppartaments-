package org.example.project.domain.entity

data class Message(
    val id: Int,
    val chatId: Int,
    val senderId: Int,
    val message: String,
    val sentAt: String?
)