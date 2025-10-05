package org.example.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.jetbrains.compose.resources.DrawableResource

class CreateListingViewModel: ViewModel() {

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var address by mutableStateOf("")
    var price by mutableStateOf(0.0)
    var imageId by mutableStateOf(0)
    var categoryId by mutableStateOf(0)
    var cityId by mutableStateOf(0)
    var adminComment by mutableStateOf("")
    var createdAt by mutableStateOf("")
    var updatedAt by mutableStateOf("")
    var isFavorite by mutableStateOf(false)
    var code by mutableStateOf("")
    var status by mutableStateOf("")

    var imageUris by mutableStateOf<List<String>>(emptyList())

    fun pickImage() {
        // Эта функция будет по-разному реализована на Android и iOS
        // В commonMain она просто объявляется
    }
}