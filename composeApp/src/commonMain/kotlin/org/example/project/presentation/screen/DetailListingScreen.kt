package org.example.project.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.ui.theme.BlueForCard
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import saintappartaments.composeapp.generated.resources.Res
import saintappartaments.composeapp.generated.resources.ava


object DetailListingScreen : Screen{
    @Composable
    override fun Content() {
        val navigator  = LocalNavigator.currentOrThrow
        DetailLisitngScreen(navigator)
    }

}


@Composable
@Preview
fun DetailLisitngScreen(navigator: Navigator?){

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
                modifier = Modifier.fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.spacedBy(250.dp)
            ) {
                IconButton(onClick = {
                    navigator?.push(ProfileScreen)
                }) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Наверх", tint = White)
                }
                IconButton(onClick = {
                   //TODO переключать избранные
                }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Наверх", tint = White)
                }
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            modifier = Modifier
                .padding(10.dp)
            , verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(200.dp)
            ) {

                Text("Заголовок", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("6,6", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Text("Город", fontSize = 20.sp)
            //TODO Список из фотографий
            Column(

            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(200.dp)
                ) {

                    Text("Заезд", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Отъезд", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(210.dp)
                ) {

                    Text("10 окт", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BlueForCard)
                    Text("12 окт", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BlueForCard)
                }

            }
            Column {
                Text("Число номеров и гостей", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(
                    "1 номер,2 взрослых, 0 детей",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlueForCard
                )
            }
            Text("Цена: 16500 руб.", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Card(modifier = Modifier.fillMaxWidth()) {

                Row(modifier = Modifier.padding(10.dp)) {
                    Image(
                        painter = painterResource(Res.drawable.ava),
                        contentDescription = "Аватар",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .border(
                                border = BorderStroke(3.dp, BlueForCard),
                                shape = RoundedCornerShape(32.dp)
                            )
                            .clip(RoundedCornerShape(32.dp))
                    )
                    Column {
                        Text("Марк", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Row {
                            Text("Контактные данные: ", fontSize = 16.sp)
                            Text("89990008912", fontSize = 16.sp, fontWeight = FontWeight.Bold)

                        }
                        Text("Написать", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = BlueForCard,
                            modifier = Modifier.clickable{
                                navigator?.push(ChatToUserScreen)
                            })

                    }

                }
            }
            //todo карту надо ли?
            Text("Отзывы гостей", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                //todo Отзывы
            Text("Описание", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            //todo описание
        }
    }
}