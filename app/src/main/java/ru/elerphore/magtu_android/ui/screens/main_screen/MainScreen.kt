package ru.elerphore.magtu_android.ui.screens.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.elerphore.magtu_android.paddingModifier

@Composable
fun MainScreen() = SchoolDays()

@Composable
fun SchoolDays() {
    Column() {
        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier
                )
            }
        }

        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier
                )
            }
        }

        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier
                )
            }
        }
    }
}