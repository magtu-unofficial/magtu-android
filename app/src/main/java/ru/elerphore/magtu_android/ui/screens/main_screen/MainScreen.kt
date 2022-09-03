package ru.elerphore.magtu_android.ui.screens.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.datetime.DayOfWeek
import ru.elerphore.magtu_android.data.SchoolDay

@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val schoolDaysList = viewModel._schoolDays

    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        schoolDaysList.groupBy { it.date.dayOfWeek }.forEach { schoolDay ->
            item {
                SchoolDayDescription(schoolDay)
            }
        }
    }
}

@Composable
fun SchoolDayDescription(schoolDay: Map.Entry<DayOfWeek, List<SchoolDay>>) =
    Card(elevation = 10.dp, modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Column(modifier = Modifier.padding(5.dp)) {
            StringDescription("День: ${schoolDay.key}")

            schoolDay.value.forEach {
                it.pairs.filter { it.subgroup?.contains("common")!! || it.subgroup.contains("second") }.forEach { lesson ->
                    Row(modifier = Modifier.padding(PaddingValues(bottom = 5.dp, top = 5.dp))) {
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(fontSize = 10.sp)) {
                                    append("(${lesson.number}) ${lesson.name}: ${lesson.teacher}: ${lesson.classroom}")
                                }
                            })
                    }
                }
            }

        }
    }

@Composable
fun StringDescription(text: String) = Text(buildAnnotatedString {
    append(text)
})