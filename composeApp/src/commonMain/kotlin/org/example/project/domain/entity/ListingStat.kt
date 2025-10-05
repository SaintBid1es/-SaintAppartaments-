package org.example.project.domain.entity

data class ListingStat(
    val listingId: Int,
    val viewsCount: Int = 0,
    val favoritesCount: Int = 0
)