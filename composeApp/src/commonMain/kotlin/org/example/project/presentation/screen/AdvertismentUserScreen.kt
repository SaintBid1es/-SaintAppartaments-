package org.example.project.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults.textContentColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.data.model.Listing
import org.example.project.data.provider.FavoriteProvider
import org.example.project.presentation.MainScreenNavigation
import org.example.project.presentation.listitem.FavoriteListItem
import org.example.project.presentation.state.FavoriteIntent
import org.example.project.ui.theme.BlueForCard
import org.example.project.ui.theme.Orange
import org.example.project.viewmodel.AdvertismentUserViewModel
import org.example.project.viewmodel.FavoriteViewModel
import org.example.project.viewmodel.MainViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

object AdvertismentUserScreen : Screen {
    @Composable
    override fun Content() {

        val navigator: Navigator? = LocalNavigator.currentOrThrow
        AdvertismentUserScreen(navigator,{})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AdvertismentUserScreen(navigator: Navigator?,onOpenProfile: () -> Unit={}) {
    var showDialog by remember { mutableStateOf(false) }
    val openDialogPeople = remember { mutableStateOf(false) }
    val viewModel = remember { AdvertismentUserViewModel() }
    val viewModelFavorite = remember { FavoriteViewModel() }
    var selectedDateRange by remember { mutableStateOf<Pair<Long?, Long?>>(null to null) }
    val allProducts = remember { FavoriteProvider.listingList }

    var filteredItems by remember { mutableStateOf(allProducts) }

    // Функция для применения фильтров
    fun applyFilters() {
        filteredItems = filterItems(
            items = allProducts,
            query = viewModel.textSearch,
            dateRange = selectedDateRange
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Шапка
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .background(BlueForCard),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "SaintAppartaments",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }

                // Поиск + выбор даты
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .border(width = 5.dp, color = Orange),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column {
                        TextField(
                            value = viewModel.textSearch,
                            onValueChange = {
                                viewModel.textSearch = it
                                // Автоматическое обновление фильтра при изменении текста
                                applyFilters()
                            },
                            label = { Text("Поиск ") },
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .fillMaxWidth()
                                .padding(10.dp),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { showDialog = true }) {
                                Icon(Icons.Filled.DateRange, contentDescription = "")
                            }
                            if (showDialog) {
                                DateRangePickerModal(
                                    onDateRangeSelected = { dateRange ->
                                        selectedDateRange = dateRange
                                        applyFilters() // Применяем фильтры после выбора даты
                                        showDialog = false
                                    },
                                    onDismiss = { showDialog = false }
                                )
                            }
                            Text("Выбранная дата: ${formatDateRange(selectedDateRange)}")
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { openDialogPeople.value = true }) {
                                Icon(Icons.Filled.Person, contentDescription = "")
                            }
                            if (openDialogPeople.value) {
                                AlertDialog(
                                    onDismissRequest = { openDialogPeople.value = false },
                                    title = { Text(text = "Пополнение баланса кошелька") },
                                    text = {
                                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                            TextField(
                                                value = viewModel.countUser,
                                                onValueChange = { newValue ->
                                                    if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                                                        viewModel.countUser = newValue
                                                    }
                                                },
                                                label = { Text("Введите кол-во людей") },
                                                singleLine = true,
                                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                            )

                                        }
                                    },
                                    confirmButton = {
                                        Button(
                                            onClick = {
                                                //TODO  тут можно вызвать функцию viewModel.addBalance()
                                                openDialogPeople.value = false
                                            }
                                        ) {
                                            Text("Подтвердить", fontSize = 18.sp)
                                        }
                                    },
                                    dismissButton = {
                                        Button(
                                            onClick = { openDialogPeople.value = false },
                                            colors = ButtonDefaults.buttonColors(),
                                            border = BorderStroke(1.dp, Color.LightGray)
                                        ) {
                                            Text("Отмена", fontSize = 18.sp)
                                        }
                                    }
                                )
                            }
                            Text("Выбранное кол-во людей: ${viewModel.countUser}")
                        }
                            //TODO Фильтрация по городу / карта
                        Button(
                            onClick = { applyFilters() }, // Явное применение фильтров по кнопке
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = BlueForCard
                            )
                        ) {
                            Text("Найти", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }

                Text(
                    text = "Недвижимости (${filteredItems.size})",
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(5.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f)
                ) {
                    items(
                        items = filteredItems, // Используем отфильтрованные элементы
                        itemContent = {
                            FavoriteListItem(
                                listing = it,
                                changeFavorite = {
                                    viewModelFavorite.processIntent(
                                        FavoriteIntent.ChangeFavorite, it
                                    )
                                },
                                viewModel = viewModelFavorite,
                                navigator = navigator
                            )
                        }
                    )
                }
            }
        }
    }
}

// Расширенная функция фильтрации с учетом даты
fun filterItems(items: List<Listing>, query: String, dateRange: Pair<Long?, Long?>): List<Listing> {
    var filtered = items

    // Фильтрация по тексту
    if (query.isNotBlank()) {
        val lowerCaseQuery = query.lowercase()
        filtered = filtered.filter {
            it.title.lowercase().contains(lowerCaseQuery) ||
                    it.description?.lowercase()?.contains(lowerCaseQuery) ?: false
        }
    }


    val (startDate, endDate) = dateRange
    if (startDate != null || endDate != null) {
        filtered = filtered.filter { listing ->

            val listingDate = listing.dateInMillis ?: return@filter true

            val afterStart = startDate == null || listingDate >= startDate
            val beforeEnd = endDate == null || listingDate <= endDate

            afterStart && beforeEnd
        }
    }

    return filtered
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            title = { Text("Select date range") },
            showModeToggle = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
        )
    }
}

fun formatDateRange(dateRange: Pair<Long?, Long?>): String {
    val (startDate, endDate) = dateRange

    return when {
        startDate == null && endDate == null -> "Выберите даты"
        startDate != null && endDate != null -> {
            "${formatDateSimple(startDate)} - ${formatDateSimple(endDate)}"
        }
        startDate != null -> "С ${formatDateSimple(startDate)}"
        else -> "До ${formatDateSimple(endDate)}"
    }
}



fun formatDateSimple(timestamp: Long?): String {
    if (timestamp == null) return ""

    // Простой расчет даты из timestamp (миллисекунды с эпохи)
    val millisecondsPerDay = 24 * 60 * 60 * 1000L
    val daysSinceEpoch = timestamp / millisecondsPerDay
    var remainingDays = daysSinceEpoch

    // Базовый год (1970)
    var year = 1970
    while (remainingDays >= if (isLeapYear(year)) 366 else 365) {
        remainingDays -= if (isLeapYear(year)) 366 else 365
        year++
    }

    val daysInMonth = intArrayOf(31, if (isLeapYear(year)) 29 else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    var month = 0
    while (month < 12 && remainingDays >= daysInMonth[month]) {
        remainingDays -= daysInMonth[month]
        month++
    }

    val day = remainingDays + 1
    return "${day.toString().padStart(2, '0')}.${(month + 1).toString().padStart(2, '0')}.$year"
}
private fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
}

