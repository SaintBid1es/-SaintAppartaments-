package org.example.project.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import saintappartaments.composeapp.generated.resources.Res
import saintappartaments.composeapp.generated.resources.apartaments3


object ChatToUserScreen : Screen{
    @Composable
    override fun Content() {
        val navigator: Navigator?= LocalNavigator.currentOrThrow
        ChatToUserScreen(navigator)
    }

}


@Composable
@Preview
fun ChatToUserScreen( navigator: Navigator?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {}){
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
            Column {
                Text("Марк", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("В сети 50 мин.назад", fontWeight = FontWeight.Bold, fontSize = 10.sp, color = Gray)
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(

            ) {
                Image(
                    painter = painterResource(Res.drawable.apartaments3),
                    contentDescription = "Аватар",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
                Column(modifier = Modifier.padding(start = 5.dp)) {
                    Text("Заголовок объявления")
                    Text("26 500 Руб")
                }
            }
        }
            //tODO сДЕЛАТЬ СПИСОК СООБЩЕНИЙ
            //TODO СДЕЛАТЬ ЗАКРЕПЛЕННОЕ НИЖНЕЕ TEXTFOLDER "Написать сообщение"


    }
}