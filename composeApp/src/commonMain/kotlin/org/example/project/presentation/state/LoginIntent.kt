package org.example.project.presentation.state

sealed class LoginIntent {
    data class InputEmail(val value: String): LoginIntent()
    data class InputPassword(val value: String): LoginIntent()
    object ClickButtonLogin: LoginIntent()
    object ClickButtonRegister: LoginIntent()
    object ClickButtonForgotPassword: LoginIntent()

}