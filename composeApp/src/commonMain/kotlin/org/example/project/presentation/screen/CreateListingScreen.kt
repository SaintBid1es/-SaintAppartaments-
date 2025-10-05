package org.example.project.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.ui.theme.BlueForCard
import org.example.project.presentation.viewmodel.CreateListingViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import saintappartaments.composeapp.generated.resources.Res
import saintappartaments.composeapp.generated.resources.camera_ic


object CreateListingScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        CreateListingScreen(navigator,{})
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CreateListingScreen(navigator: Navigator?,onBack:()->Unit={}) {
    val viewModel = remember { CreateListingViewModel() }
    val openDialog = remember { mutableStateOf(false) }
    val category = listOf("Категория 1", "Категория 2", "Категория 3", "Категория 4", "Категория 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(category[0]) }
    val city = listOf("Город 1", "Город 2", "Город 3", "Город 4", "Город 5")
    var expandedCity by remember { mutableStateOf(false) }
    var selectedOptionCity by remember { mutableStateOf(city[0]) }
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
                Text(
                    "Создание объявления",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start

        ) {
            Text(
                "Заголовок",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = viewModel.title,
                onValueChange = { viewModel.title = it },
                label = { Text("Введите Заголовок") },

                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Text(
                "Описание",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = viewModel.description,
                onValueChange = { viewModel.description = it },
                label = { Text("Введите Описание ") },

                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Text(
                "Адресс",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = viewModel.address,
                    onValueChange = { viewModel.address = it },
                    label = { Text("Введите Адресс ") },


                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                IconButton(onClick = {
                    openDialog.value = true
                }) {
                    Icon(Icons.Filled.Check, contentDescription = "")
                }
                if (openDialog.value) {
                    AlertDialog(
                        onDismissRequest = { openDialog.value = false },
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
                                onClick = { openDialog.value = false },
                                colors = ButtonDefaults.buttonColors(),
                                border = BorderStroke(1.dp, Color.LightGray)
                            ) {
                                Text("Отмена", fontSize = 22.sp)
                            }
                        })
                }

            }
            Text(
                "Стоимость",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = viewModel.price.toString(),
                onValueChange = { viewModel.price = it.toDouble() },
                label = { Text("Введите стоимость ") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            Text(
                "Категории",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                TextField(
                    value = selectedOption,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                        .menuAnchor(),
                    readOnly = true,
                    label = {
                        Text(
                            "Label",
                            style = TextStyle(color = Color.Black, fontSize = 12.sp)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = if (expanded) "Collapse" else "Expand",
                            modifier = Modifier.rotate(if (expanded) 180f else 0f),
                            tint = Color.White,
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(

                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth().background(Color.White)
                ) {
                    category.forEachIndexed { index, option ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOption = option
                                expanded = false
                            },
                            text = { // Добавлен обязательный параметр text
                                Text(
                                    text = option, // Добавлен параметр text
                                    style = TextStyle(fontSize = 16.sp, color = Color(0xff263238))
                                )
                            }
                        )
                        // Show divider after every item except the last one
                        if (index < category.lastIndex) {
                            Divider(
                                color = Color.LightGray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
            Text(
                "Город",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            ExposedDropdownMenuBox(
                expanded = expandedCity,
                onExpandedChange = { expandedCity = it }
            ) {
                TextField(
                    value = selectedOptionCity,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                        .menuAnchor(),
                    readOnly = true,
                    label = {
                        Text(
                            "Label",
                            style = TextStyle(color = Color.Black, fontSize = 12.sp)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = if (expandedCity) "Collapse" else "Expand",
                            modifier = Modifier.rotate(if (expandedCity) 180f else 0f),
                            tint = Color.White,
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(

                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
                ExposedDropdownMenu(
                    expanded = expandedCity,
                    onDismissRequest = { expandedCity = false },
                    modifier = Modifier.fillMaxWidth().background(Color.White)
                ) {
                    city.forEachIndexed { index, option ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionCity = option
                                expandedCity = false
                            },
                            text = { // Добавлен обязательный параметр text
                                Text(
                                    text = option, // Добавлен параметр text
                                    style = TextStyle(fontSize = 16.sp, color = Color(0xff263238))
                                )
                            }
                        )
                        // Show divider after every item except the last one
                        if (index < city.lastIndex) {
                            Divider(
                                color = Color.LightGray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
            Text("Внешний вид:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            IconButton(
                onClick = {
                    // Вызываем общую функцию
                    viewModel.pickImage()
                }, modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.camera_ic),
                    contentDescription = "Добавить фото",
                    modifier = Modifier.size(25.dp)
                )
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 4,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                repeat(8) { index ->
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Фото ${index + 1}", color = Color.White, fontSize = 10.sp)
                    }
                }
            }
            Button(
                onClick = {

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)

            ) {
                Text("Подтвердить")
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }
    }
}