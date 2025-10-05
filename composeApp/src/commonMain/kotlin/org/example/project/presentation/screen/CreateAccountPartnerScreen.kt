package org.example.project.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.project.ui.theme.BlueForCard
import org.example.project.viewmodel.CreateAccountPartnerViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview



object CreateAccountPartnerScreen : Screen {
    @Composable
    override fun Content() {
        CreateAccountPartnerScreen({})
    }

}





@Composable
@Preview
fun CreateAccountPartnerScreen(onBack:()->Unit={}){
    val viewmodel = remember { CreateAccountPartnerViewModel() }
    val navigator = LocalNavigator.current
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
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Наверх", tint = White)
                }
                Text("Создайте аккаунт партнера", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            }
        }
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Имя", fontSize = 20.sp)
            TextField(
                value = viewmodel.name,
                onValueChange = { viewmodel.name = it },
                modifier = Modifier.fillMaxWidth() ,
                label = {Text("Введите Имя")}
            )
            Text("Фамилия", fontSize = 20.sp)
            TextField(
                value = viewmodel.surname,
                onValueChange = { viewmodel.surname = it },
                modifier = Modifier.fillMaxWidth() ,
                label = {Text("Введите фамилию")}
            )
            Text("Телефон", fontSize = 20.sp)
            TextField(
                value = viewmodel.telephone,
                onValueChange = { viewmodel.telephone = it },
                modifier = Modifier.fillMaxWidth() ,
                label = {Text("Введите номер телефона")}
            )

            Text("Введите адрес электронной почты", fontSize = 20.sp)
            TextField(
                value = viewmodel.email,
                onValueChange = { viewmodel.email = it },
                modifier = Modifier.fillMaxWidth() ,
                label = {Text("Введите адрес электронной почты")}
            )
            Button(onClick = {}, modifier = Modifier
                .fillMaxWidth()

            ){
                Text("Продолжить")
            }
        }
    }
}