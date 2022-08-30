package ru.elerphore.magtu_android.ui.screens.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.http_client.WC

private val paddingModifier  = Modifier
    .padding(10.dp)
    .fillMaxWidth()
    .clickable {
        CoroutineScope(Dispatchers.IO).launch {
            val items = WC.wc.getSchoolDays()
            println("ITEMS: $items")
        }
    }

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