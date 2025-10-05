package org.example.project.presentation.state

sealed class LoginState {
    object Loading: LoginState()
    object Idle: LoginState()
    data class Success(val email:String,val password: String) : LoginState()
    data class Error(
        val message: String
    ): LoginState()
}