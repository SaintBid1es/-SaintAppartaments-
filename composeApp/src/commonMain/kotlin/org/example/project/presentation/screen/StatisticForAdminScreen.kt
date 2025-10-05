package org.example.project.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.ui.theme.BlueForCard
import org.jetbrains.compose.ui.tooling.preview.Preview


object StatisticForAdminScreen : Screen{
    @Composable
    override fun Content() {
        val navigator: Navigator? = LocalNavigator.currentOrThrow
        StatisticForAdminScreen(navigator)
    }

}


@Composable
@Preview
fun StatisticForAdminScreen(navigator: Navigator?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(BlueForCard),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

            ) {
                IconButton(onClick = {
                    navigator?.push(ProfileScreen)
                }) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Наверх", tint = White)
                }
                Text("Статистика для администратора", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            }
        }
            //todo ДИАГРАМЫ СДЕЛАТЬ СТАТИСТКИА
    }
}