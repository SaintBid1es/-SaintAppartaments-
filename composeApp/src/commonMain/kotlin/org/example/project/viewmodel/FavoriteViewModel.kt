package org.example.project.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.project.data.model.Listing
import org.example.project.data.provider.FavoriteProvider
import org.example.project.presentation.state.FavoriteIntent
import org.example.project.presentation.state.FavoriteState
import kotlin.collections.set

class FavoriteViewModel: ViewModel() {
    private val _state = MutableStateFlow<FavoriteState>(FavoriteState.Loading)
    val state: StateFlow<FavoriteState> = _state
    val _favorites = mutableStateMapOf<Int, Boolean>()

    init {
        _state.value = FavoriteState.Success(FavoriteProvider.listingList.filter { it.isFavorite })
    }


    fun isFavorite(productModels: Listing): Boolean {
        return _favorites[productModels.id] ?: productModels.isFavorite
    }

    fun processIntent(intent: FavoriteIntent, product: Listing) {
        when (intent) {
            FavoriteIntent.ChangeFavorite -> {
                val newStatus = !isFavorite(product)
                _favorites[product.id] = newStatus

                // Обновляем состояние
                val current = (_state.value as? FavoriteState.Success)?.favorites ?: emptyList()
                val updatedList = if (newStatus) {
                    current + product.copy(isFavorite = true)
                } else {
                    current.filterNot { it.id == product.id }
                }

                _state.value = FavoriteState.Success(updatedList)
            }
        }
    }
}