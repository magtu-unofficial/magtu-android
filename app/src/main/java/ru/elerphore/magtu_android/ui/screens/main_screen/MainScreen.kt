package ru.elerphore.magtu_android.ui.screens.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.data.SchoolDay
import ru.elerphore.magtu_android.http_client.WC


private val paddingModifier  = Modifier
    .padding(10.dp)
    .fillMaxWidth()

@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()

    val schoolDaysList = viewModel._schoolDays

    Column {
        schoolDaysList.forEach { schoolDay ->
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = schoolDay.displayName, modifier = paddingModifier)
                schoolDay.lessons.forEach { lesson ->
                    Text(text = "${lesson.name}", modifier = paddingModifier)
                    Text(text = "${lesson.teacher}", modifier = paddingModifier)
                    Text(text = "${lesson.subgroup}", modifier = paddingModifier)
                }
            }
        }
    }
}

@Composable
fun SchoolDays() {

//    LazyColumn() {
//        items(3) {

//        }
//    }
//    Column() {

//    }
}
