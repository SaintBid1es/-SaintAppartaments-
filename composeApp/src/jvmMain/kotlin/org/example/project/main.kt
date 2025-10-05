package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.presentation.screen.MainScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SaintAppartaments",
    ) {
        Navigator(MainScreen)

    }
}