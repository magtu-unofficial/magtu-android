package ru.elerphore.magtu_android.ui.screens.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


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
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                            ) {
                                append(schoolDay.displayName)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    schoolDay.pairs.sortedBy { it.number }.forEach { lesson ->
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                                ) {
                                    append(lesson.teacher!!)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                                ) {
                                    append(lesson.name!!)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                                ) {
                                    append(lesson.classroom!!)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
