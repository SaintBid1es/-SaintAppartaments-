package org.example.project.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.data.model.User
import org.example.project.presentation.screen.ActivityLogForAdminScreen
import org.example.project.presentation.screen.AddToBalanceScreen
import org.example.project.presentation.screen.AdvertismentUserScreen
import org.example.project.presentation.screen.CategoryManagmentScreen
import org.example.project.presentation.screen.ChatScreen
import org.example.project.presentation.screen.CreateAccountPartnerScreen
import org.example.project.presentation.screen.CreateListingScreen
import org.example.project.presentation.screen.FavoriteScreen
import org.example.project.presentation.screen.ForgotPasswordScreen
import org.example.project.presentation.screen.MainScreen
import org.example.project.presentation.screen.ModerationListingScreen
import org.example.project.presentation.screen.ProfileScreen
import org.example.project.presentation.screen.ReservationScreen
import org.example.project.presentation.screen.ReviewScreen
import org.example.project.presentation.screen.TradeHistoryScreen
import org.example.project.presentation.screen.UpdateInformationScreen
import org.example.project.presentation.screen.UserManagmentScreen


object MainScreenNavigation: Screen{
    @Composable
    override fun Content() {
        MainScreenNavigation()
    }

}

@Composable
fun MainScreenNavigation() {
    var currentScreen by remember { mutableStateOf<MainScreenState>(MainScreenState.Advertisment) }
    var selectedUser by remember { mutableStateOf<User?>(null) } // хранение выбранного пользователя

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            when (currentScreen) {
                MainScreenState.Advertisment -> AdvertismentUserScreen(
                    onOpenProfile = { currentScreen = MainScreenState.Profile },
                    navigator = null
                )

                MainScreenState.Favorite -> FavoriteScreen()
                MainScreenState.Reservation -> ReservationScreen()
                MainScreenState.Profile -> ProfileScreen(
                    onOpenActivityLog = { currentScreen = MainScreenState.ActivityLog },
                    onOpenTradeHistory = { currentScreen = MainScreenState.TradeHistory },
                    onOpenUserManagement = { currentScreen = MainScreenState.UserManagement },
                    onOpenCategoryManagement = {
                        currentScreen = MainScreenState.CategoryManagement
                    },
                    onOpenModeration = { currentScreen = MainScreenState.Moderation },
                    UpdateInformationScreen = { user ->
                        selectedUser = user
                        currentScreen = MainScreenState.UpdateInformationScreen
                    },
                    ReviewScreen = { currentScreen = MainScreenState.ReviewScreen },
                    AddToBalanceScreen = { currentScreen = MainScreenState.AddToBalanceScreen },
                    CreateAccountPartnerScreen = { currentScreen = MainScreenState.CreateAccountPartnerScreen },
                    CreateListingScreen = { currentScreen = MainScreenState.CreateListingScreen},
                )

                MainScreenState.ActivityLog -> ActivityLogForAdminScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                    navigator = null
                )

                MainScreenState.TradeHistory -> TradeHistoryScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                    navigator = null
                )
                MainScreenState.UserManagement -> UserManagmentScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                    navigator = null
                )
                MainScreenState.CategoryManagement -> CategoryManagmentScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                    navigator = null
                )
                MainScreenState.Moderation -> ModerationListingScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                    navigator = null
                )

                MainScreenState.Chat -> ChatScreen()
                MainScreenState.AddToBalanceScreen -> AddToBalanceScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                    navigator = null
                )
                MainScreenState.CreateAccountPartnerScreen -> CreateAccountPartnerScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                   )
                MainScreenState.CreateListingScreen -> CreateListingScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                    navigator = null)
                MainScreenState.ReviewScreen -> ReviewScreen(
                    onBack = { currentScreen = MainScreenState.Profile },
                    navigator = null
                )
                MainScreenState.UpdateInformationScreen -> selectedUser?.let { user ->
                    UpdateInformationScreen(
                        navigator = null,
                        user = user,
                        onBack = { currentScreen = MainScreenState.Profile }
                    )
                }
            }
        }

        // Нижнее меню показываем только на основных экранах
        if (currentScreen in listOf(
                MainScreenState.Advertisment,
                MainScreenState.Favorite,
                MainScreenState.Reservation,
                MainScreenState.Profile,
                MainScreenState.Chat
            )
        ) {
            BottomNavigationMenuWithState(
                selectedTab = when (currentScreen) {
                    MainScreenState.Advertisment -> 0
                    MainScreenState.Favorite -> 1
                    MainScreenState.Reservation -> 2
                    MainScreenState.Profile -> 3
                    MainScreenState.Chat -> 4
                    else -> -1
                },
                onTabSelected = {
                    currentScreen = when (it) {
                        0 -> MainScreenState.Advertisment
                        1 -> MainScreenState.Favorite
                        2 -> MainScreenState.Reservation
                        3 -> MainScreenState.Profile
                        4 -> MainScreenState.Chat
                        else -> MainScreenState.Advertisment
                    }
                }
            )
        }
    }
}



@Composable
fun BottomNavigationMenuWithState(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val items = listOf("Найти", "Сохраненное", "Бронирования", "Мой аккаунт","Чат")
    val selectedIcons = listOf(
        Icons.Filled.Search,
        Icons.Outlined.Favorite,
        Icons.Filled.Backpack,
        Icons.Filled.Person,
        Icons.Filled.Chat,
    )
    val unselectedIcons = listOf(
        Icons.Filled.Search,
        Icons.Outlined.Favorite,
        Icons.Filled.Backpack,
        Icons.Filled.Person,
        Icons.Filled.Chat,

    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 5.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (selectedTab == index) selectedIcons[index] else unselectedIcons[index],
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                alwaysShowLabel = true
            )
        }
    }
}
sealed class MainScreenState {
    data object Advertisment : MainScreenState()
    data object Favorite : MainScreenState()
    data object Reservation : MainScreenState()
    data object Profile : MainScreenState()
    data object Moderation : MainScreenState()
    data object CategoryManagement : MainScreenState()
    data object UserManagement : MainScreenState()
    data object TradeHistory : MainScreenState()
    data object Chat : MainScreenState()
    data object CreateListingScreen : MainScreenState()
    data object CreateAccountPartnerScreen : MainScreenState()
    data object AddToBalanceScreen : MainScreenState()
    data object ReviewScreen : MainScreenState()
    data object UpdateInformationScreen : MainScreenState()

    // вложенные экраны
    data object ActivityLog : MainScreenState()
}
