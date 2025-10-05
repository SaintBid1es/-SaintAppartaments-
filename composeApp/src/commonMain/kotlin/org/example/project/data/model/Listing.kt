package org.example.project.data.model

import org.jetbrains.compose.resources.DrawableResource

data class Listing(
    val id: Int,
    val userId: Int,
    val categoryId: Int?,
    val cityId: Int?,
    val imageId: DrawableResource,
    val title: String,
    val description: String?,
    val status: String = "pending",
    val price: Double,
    val address: String?,
    val adminComment: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val isFavorite: Boolean,
    val dateInMillis: Long?
)