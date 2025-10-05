package org.example.project.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.project.domain.entity.User
import org.example.project.presentation.ui.theme.BlueForCard
import org.example.project.presentation.viewmodel.ProfileViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import saintappartaments.composeapp.generated.resources.Res
import saintappartaments.composeapp.generated.resources.ava
import saintappartaments.composeapp.generated.resources.ic_star
import saintappartaments.composeapp.generated.resources.plusv2


object ProfileScreen : Screen{
    @Composable
    override fun Content() {
        ProfileScreen({},{},{},{},{},{},{},{},{},{})
    }
}





@Composable
@Preview
fun ProfileScreen(onOpenActivityLog:()->Unit,
                  onOpenModeration:()->Unit,
                  onOpenCategoryManagement:()->Unit,
                  onOpenUserManagement:()->Unit,
                  onOpenTradeHistory:()->Unit,
                  UpdateInformationScreen:(User)->Unit,
                  ReviewScreen:()->Unit,
                  AddToBalanceScreen:()->Unit,
                  CreateAccountPartnerScreen:()->Unit,
                  CreateListingScreen:()->Unit,


                  ){
    val openDialogSendStatistic = remember { mutableStateOf(false) }

    val viewModel = remember { ProfileViewModel() }
    val user = User(0,1,"Mark","vesna","kleshrama13@gmail.com",null,"1",null,10.0,false,null,false,null)
    val scrollState = rememberScrollState()
    val navigator = LocalNavigator.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp)
                .padding(bottom = 125.dp)
               ,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    navigator?.push(MainScreen)
                }) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Наверх")
                }
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {
                        UpdateInformationScreen(user)
                    }) {
                        Icon(Icons.Filled.Draw, contentDescription = "Уведомления")
                    }

                }
            }

            Row {


                Image(
                    painter = painterResource(Res.drawable.ava),
                    contentDescription = "Аватар",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .border(
                            border = BorderStroke(3.dp, BlueForCard),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .clip(RoundedCornerShape(32.dp))
                )
                IconButton(onClick = {

                }
                , ){
                   Image(painterResource(Res.drawable.plusv2), contentDescription = "")
                }
            }

            Text("Имя", fontSize = 20.sp)
            Text("В приложении с 2025 года", fontSize = 16.sp)
            Text("Номер профиля id ", fontSize = 16.sp)


            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("5,0  ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                repeat(5) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_star),
                        contentDescription = "Звезда рейтинга",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Text("1 Отзыв", color = BlueForCard, fontWeight = FontWeight.Bold, modifier = Modifier
                .clickable{
                    ReviewScreen
                })

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Финансы")
                IconButton(onClick = AddToBalanceScreen) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("user.balance.toString()", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("SaintAppartament кошелёк")
                }
            }

            Column() {
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Стать продавцом",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick =CreateAccountPartnerScreen) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Выставить объявление",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick = CreateListingScreen) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Отправка отчетностей на почту",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick = {
                        openDialogSendStatistic.value = true
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }

                }
                if (openDialogSendStatistic.value) {
                    AlertDialog(
                        onDismissRequest = { openDialogSendStatistic.value = false},
                        title = { Text(text = "Отправка на почту") },
                        text = { Text("Введите почту для отправки отчетностей") },
                        confirmButton = {
                            Button(onClick = {
                                openDialogSendStatistic.value = false

                            }) {
                                Text("Подтвердить", fontSize = 22.sp)
                            }
                            TextField(
                                value = viewModel.email,
                                onValueChange = { viewModel.email = it },
                                label = { Text("Input your email") }
                            )

                        },
                        dismissButton = {
                            Button(
                                onClick = { openDialogSendStatistic.value = false }, colors = ButtonDefaults.buttonColors(), border = BorderStroke(1.dp, Color.LightGray)) {
                                Text("Отмена", fontSize = 22.sp)
                            }
                        })
                }

                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Транзакции",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick = onOpenTradeHistory) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Управление пользователями",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick = onOpenUserManagement) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Экспорт/Импорт Базы данных",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick = {
                        //todo ЭКСПОРТ ИМПОРТ БД
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Журнал действий персонала",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick =
                        onOpenActivityLog
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Статистика для администрирования",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick = {
                     //todo нада
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Управление категориями",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick = onOpenCategoryManagement) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Модерация объявлений",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(onClick = onOpenModeration) {
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Перейти")
                    }
                }

            }

        }
    }
}




