package org.example.project.presentation.state

import org.example.project.data.model.Listing

sealed class FavoriteState{
    object Loading: FavoriteState()

    data class Success(
        val favorites: List<Listing>
    ): FavoriteState()

    data class Error(
        val message: String
    ): FavoriteState()
}