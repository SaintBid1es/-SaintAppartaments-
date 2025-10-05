package org.example.project.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.presentation.MainScreenNavigation
import org.example.project.presentation.screen.AdvertismentUserScreen
import org.example.project.presentation.screen.ForgotPasswordScreen
import org.example.project.presentation.screen.MainScreen
import org.example.project.presentation.screen.RegisterScreen
import org.example.project.presentation.state.LoginIntent
import org.example.project.presentation.state.LoginState

class MainViewModel : ViewModel(){
    private val _state= MutableStateFlow<LoginState>(LoginState.Loading)
    val state: StateFlow<LoginState> = _state
    var email by mutableStateOf("")
    var password by mutableStateOf("")


    fun proccesIntent(intent: LoginIntent,navigator: Navigator){
        when(intent){
            LoginIntent.ClickButtonForgotPassword -> {
                _state.value = LoginState.Loading
                _state.value = LoginState.Idle
                navigator.push(ForgotPasswordScreen)
            }
            LoginIntent.ClickButtonLogin -> {
                try {
                    viewModelScope.launch {
                            _state.value = LoginState.Loading
                            navigator.push(MainScreenNavigation)
                    }

                   // navigator.push(MainScreen)
                }catch (e: Exception){

                }
            }
            LoginIntent.ClickButtonRegister -> {
                navigator.push(RegisterScreen)
            }
            is LoginIntent.InputEmail -> {
                email = intent.value
            }
            is LoginIntent.InputPassword -> {
                password = intent.value
            }

        }
    }



    init {
            _state.value = LoginState.Idle
    }


}