package org.example.project.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.ui.tooling.preview.Preview



object ForgotPasswordScreen : Screen{
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ForgotPasswordScreen(navigator)
    }

}



@Composable
@Preview
fun ForgotPasswordScreen(navigator: Navigator?){
    var email by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(25.dp)

        ) {
            IconButton(
                onClick = {
                    navigator?.push(MainScreen)
                },
                modifier = Modifier.padding()
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "return on Main window")
            }
            Text(text = "Forgot Password", fontSize = 20.sp, textAlign = TextAlign.Center)
        }
        Row {
            TextField(
                value = email,
                onValueChange = {email=it},
                label = {Text("Input your email")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            IconButton(
                onClick = {
                    openDialog.value = true
                }
            ) {
                Icon(Icons.Filled.Check, contentDescription = "Info on check email verification")
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
                            value = code,
                            onValueChange = { code = it },
                            label = { Text("Input your code") }
                        )

                    },
                    dismissButton = {
                        Button(
                            onClick = { openDialog.value = false }, colors = androidx.compose.material3.ButtonDefaults.buttonColors(), border = BorderStroke(1.dp, Color.LightGray)) {
                            Text("Отмена", fontSize = 22.sp)
                        }
                    })
            }
        }
        Button(
            onClick = {

            },
            modifier = Modifier.width(200.dp)
        ) {
            Text("Confirm")
        }
    }
}