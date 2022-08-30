package ru.elerphore.magtu_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.elerphore.magtu_android.data.DB
import ru.elerphore.magtu_android.ui.Main

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DB.provide(this)

        setContent {
            Main()
        }
    }
}



