package org.example.project.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.data.model.Message
import org.example.project.presentation.listitem.FavoriteListItem
import org.example.project.presentation.listitem.MessageListItem
import org.example.project.presentation.state.FavoriteIntent
import org.example.project.ui.theme.BlueForCard
import org.example.project.viewmodel.ChatViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


object ChatScreen  : Screen{
    @Composable
    override fun Content() {
        ChatScreen()
    }
}


@Composable
@Preview
fun ChatScreen(){
    val viewModel = remember { ChatViewModel() }
    val message = listOf(Message(id = 1, 1, 1, "adsad", "text"))
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
            Text("Чат", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

        ) {
            val filterMessage = filterMessage(message,viewModel.textSearch)
            items(
                items = filterMessage,
                itemContent = {
                    MessageListItem(message = it)
                }

            )
        }

    }
}
fun filterMessage(list: List<Message>, filterText: String):List<Message>{
    var  filterMessage:List<Message> = list
    if (!filterText.isEmpty()) {
        filterMessage =  list.filter {
            it.message.toString() == filterText.lowercase()
        }

    }
    return filterMessage

}