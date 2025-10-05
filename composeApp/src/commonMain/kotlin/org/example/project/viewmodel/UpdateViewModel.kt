package org.example.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UpdateViewModel: ViewModel() {
    var name by mutableStateOf("")
    var surName by mutableStateOf("")
    var code by mutableStateOf("")
    var phone by mutableStateOf("")
    var email by mutableStateOf("")
}