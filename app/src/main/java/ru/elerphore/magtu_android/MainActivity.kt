package ru.elerphore.magtu_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.http_client.WebClient
import ru.elerphore.magtu_android.ui.Main

val wc = WebClient()

val paddingModifier  = Modifier
    .padding(10.dp)
    .fillMaxWidth()
    .clickable {
        CoroutineScope(IO).launch {
            val items = wc.getSchoolDays()
            println("ITEMS: $items")
        }
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Main()
        }
    }
}



