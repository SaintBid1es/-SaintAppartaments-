package org.example.project.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ChatViewModel: ViewModel() {
    var textSearch by  mutableStateOf("")
}