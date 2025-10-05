package org.example.project.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateAccountPartnerViewModel: ViewModel() {
    var email by mutableStateOf("")
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var telephone by mutableStateOf("")
}