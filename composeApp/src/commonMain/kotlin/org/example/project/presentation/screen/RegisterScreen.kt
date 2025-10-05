package org.example.project.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.viewmodel.RegisterViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


object RegisterScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        RegisterScreen(navigator)
    }

}


@Composable
@Preview
fun RegisterScreen(navigator: Navigator){
    val viewModel = remember { RegisterViewModel() }
    val openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    navigator.pop()
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
            Text(text = "Регистрация аккаунта", fontSize = 20.sp)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
            horizontalAlignment = Alignment.Start

        ) {
            Text("Имя", fontSize = 16.sp, modifier = Modifier.padding(start = 10.dp), fontWeight = FontWeight.Bold)
            TextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = { Text("Введите Имя ") },
                
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Text("Фамилия", fontSize = 16.sp, modifier = Modifier.padding(start = 10.dp), fontWeight = FontWeight.Bold)
            TextField(
                value = viewModel.surName,
                onValueChange = { viewModel.surName = it },
                label = { Text("Введите Фамилию ") },

                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Text("Почта", fontSize = 16.sp, modifier = Modifier.padding(start = 10.dp), fontWeight = FontWeight.Bold)
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                TextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    label = { Text("Введите Почту ") },


                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                IconButton(onClick = {
                    openDialog.value = true
                }) {
                    Icon(Icons.Filled.Check, contentDescription = "")
                }
                if (openDialog.value) {
                    AlertDialog(
                        onDismissRequest = { openDialog.value = false},
                        title = { Text(text = "Подтверждение Почты") },
                        text = { Text("Введите код для завершения регистрации.") },
                        confirmButton = {
                            Button(onClick = {
                                openDialog.value = false

                                }) {
                                Text("Подтвердить", fontSize = 22.sp)
                            }
                            TextField(
                                value = viewModel.code,
                                onValueChange = { viewModel.code = it },
                                label = { Text("Input your code") }
                            )

                        },
                        dismissButton = {
                            Button(
                                onClick = { openDialog.value = false }, colors = ButtonDefaults.buttonColors(), border = BorderStroke(1.dp, Color.LightGray)) {
                                Text("Отмена", fontSize = 22.sp)
                            }
                        })
                }

            }
            Text("Номер телефона", fontSize = 16.sp, modifier = Modifier.padding(start = 10.dp), fontWeight = FontWeight.Bold)
            TextField(
                value = viewModel.phone,
                onValueChange = { viewModel.phone = it },
                label = { Text("Введите номер телефона ") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            Button(onClick = {

            }, modifier = Modifier.fillMaxWidth()
                .padding(15.dp)) {
                Text("Подтвердить")
            }


        }
    }
}