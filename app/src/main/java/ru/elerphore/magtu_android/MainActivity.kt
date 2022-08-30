package ru.elerphore.magtu_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.data.Database
import ru.elerphore.magtu_android.data.Settings
import ru.elerphore.magtu_android.http_client.WebClient
import ru.elerphore.magtu_android.ui.BottomNavigation
import ru.elerphore.magtu_android.ui.Main
import ru.elerphore.magtu_android.ui.screens.NavigationGraph

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

        CoroutineScope(IO).launch {

            val db = Room.databaseBuilder(
                applicationContext,
                Database::class.java, "magtu"
            ).build()

            val settingsDao = db.settingsDao()
            val list = settingsDao.getAll()

            if(list.isEmpty()) {
                settingsDao.insert(Settings(0, "ИСп-19-4", false))
            }

            println("LIST DATABASE: $list")
        }

            setContent {
                Main()
            }
    }
}



