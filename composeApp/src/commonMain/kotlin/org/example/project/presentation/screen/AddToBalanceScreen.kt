package org.example.project.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.ui.theme.BlueForCard
import org.example.project.presentation.viewmodel.AddToBalanceViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


object AddToBalanceScreen : Screen {
    @Composable
    override fun Content() {
        val navigator: Navigator? = LocalNavigator.currentOrThrow
        AddToBalanceScreen(navigator,{})
    }

}

@Composable
@Preview
fun AddToBalanceScreen(navigator: Navigator?,onBack:()->Unit={}) {
    val viewModel: AddToBalanceViewModel = remember { AddToBalanceViewModel() }
    val openDialogAddBalance = remember { mutableStateOf(false) }
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
                horizontalArrangement = Arrangement.spacedBy(90.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Наверх", tint = White)
                }
                Text("Пополнение баланса", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            }

        }
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            TextField(
                value = viewModel.balance,
                onValueChange = {viewModel.balance=it},
                label = {
                    Text("Введите сумму баланса")
                }, modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = {
                openDialogAddBalance.value = true
            }, modifier = Modifier.fillMaxWidth()){
                Text("Подтвердить")
            }
            if (openDialogAddBalance.value) {
                AlertDialog(
                    onDismissRequest = { openDialogAddBalance.value = false },
                    title = { Text(text = "Пополнение баланса кошелька") },
                    text = {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            TextField(
                                value = viewModel.cardNumber,
                                onValueChange = { viewModel.cardNumber = it },
                                label = { Text("Номер карты") },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            TextField(
                                value = viewModel.cardDate,
                                onValueChange = { viewModel.cardDate = it },
                                label = { Text("MM/YY") },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            TextField(
                                value = viewModel.cardCvc,
                                onValueChange = { viewModel.cardCvc = it },
                                label = { Text("CVC") },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                //TODO  тут можно вызвать функцию viewModel.addBalance()
                                openDialogAddBalance.value = false
                            }
                        ) {
                            Text("Подтвердить", fontSize = 18.sp)
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { openDialogAddBalance.value = false },
                            colors = ButtonDefaults.buttonColors(),
                            border = BorderStroke(1.dp, Color.LightGray)
                        ) {
                            Text("Отмена", fontSize = 18.sp)
                        }
                    }
                )
            }

        }

    }
}