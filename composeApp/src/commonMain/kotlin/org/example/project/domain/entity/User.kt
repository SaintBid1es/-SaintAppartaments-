package org.example.project.domain.entity
data class User(
    val id: Int,
    val roleId: Int?,
    val name: String,
    val surname: String,
    val email: String,
    val photo: String?,
    val password: String,
    val phone: String?,
    val balance: Double?,
    val isBlocked: Boolean = false,
    val lastSeen: String?,
    val isOnline: Boolean = false,
    val createdAt: String?
)