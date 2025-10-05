package org.example.project.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.domain.entity.Listing
import org.example.project.data.provider.FavoriteProvider
import org.example.project.presentation.listitem.FavoriteListItem
import org.example.project.presentation.state.FavoriteIntent
import org.example.project.presentation.ui.theme.BlueForCard
import org.example.project.presentation.viewmodel.FavoriteViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview



object FavoriteScreen  : Screen{
    @Composable
    override fun Content() {
        FavoriteScreen()
    }
}


@Composable
@Preview
fun FavoriteScreen(){
    val navigator = LocalNavigator.currentOrThrow
    val favorites:List<Listing> = FavoriteProvider.listingList
    val viewModel = remember { FavoriteViewModel() }
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
                Text("Избранные", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),

        ) {
            items(
                items = favorites,
                itemContent = {
                    FavoriteListItem(
                        listing = it, changeFavorite = {
                            viewModel.processIntent(
                                FavoriteIntent.ChangeFavorite, it
                            )
                        }, viewModel = viewModel,
                        navigator = navigator
                    )
                }
            )
        }
    }
}