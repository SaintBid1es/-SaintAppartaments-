package org.example.project.data.model

data class Message(
    val id: Int,
    val chatId: Int,
    val senderId: Int,
    val message: String,
    val sentAt: String?
)