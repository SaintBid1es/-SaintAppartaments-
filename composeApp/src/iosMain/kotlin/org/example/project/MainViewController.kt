package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.presentation.screen.MainScreen

fun MainViewController() = ComposeUIViewController {    Navigator(MainScreen) }