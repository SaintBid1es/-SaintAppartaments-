package org.example.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class AddToBalanceViewModel: ViewModel() {
    var balance by mutableStateOf("")
    var cardNumber by mutableStateOf("")
    var cardCvc by mutableStateOf("")
    var cardDate by mutableStateOf("")
    var email by mutableStateOf("")
}