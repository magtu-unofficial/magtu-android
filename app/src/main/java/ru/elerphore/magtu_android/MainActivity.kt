package ru.elerphore.magtu_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.http_client.WebClient

val wc = WebClient()

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    val paddingModifier  = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .clickable {
            CoroutineScope(IO).launch {
                val items = wc.getSchoolDays()
                println("ITEMS: $items")
            }
        }

    Column() {
        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier)
            }
        }

        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier)
            }
        }

        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier)
            }
        }
    }
}