package org.example.project.data.provider

import org.example.project.domain.entity.Listing
import saintappartaments.composeapp.generated.resources.Res
import saintappartaments.composeapp.generated.resources.apartament22
import saintappartaments.composeapp.generated.resources.apartaments3
import saintappartaments.composeapp.generated.resources.appartaments1

    object FavoriteProvider {
        val listing = Listing(
            id = 1,
            userId = 1,
            categoryId = 1,
            cityId = 1,
            title = "Недвижимость 1",
            description = "Описание",
            status = "active",
            price = 10000.0,
            address = "Address",
            createdAt = "11.09.2025",
            adminComment = null,
            updatedAt = null,
            imageId = Res.drawable.appartaments1,
            isFavorite = false,
            dateInMillis = null,
        )
    val listingList = listOf(
        listing,
        Listing(
            id = 2,
            userId = 1,
            categoryId = 1,
            cityId = 1,
            title = "Недвижимость 1",
            description = "Описание",
            status = "active",
            price = 10000.0,
            address = "Address",
            createdAt = "11.09.2025",
            adminComment = null,
            updatedAt = null,
            imageId = Res.drawable.apartament22,
            isFavorite = true,
            dateInMillis = null,
        ),
        Listing(
            id = 3,
            userId = 1,
            categoryId = 1,
            cityId = 1,
            title = "Недвижимость",
            description = "Описание",
            status = "active",
            price = 12000.0,
            address = "Address",
            createdAt = "11.09.2025",
            adminComment = null,
            updatedAt = null,
            imageId = Res.drawable.apartaments3,
            isFavorite = true,
            dateInMillis = null,
        )
    )
}