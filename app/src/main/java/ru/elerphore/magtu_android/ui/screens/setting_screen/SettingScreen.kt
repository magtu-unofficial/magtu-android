package ru.elerphore.magtu_android.ui.screens.setting_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*

@Composable
fun SettingScreen() {
    Column() {
        var textOne by remember { mutableStateOf("Hello") }
        var textTwo by remember { mutableStateOf("Hello") }

        TextField(
            value = textOne,
            onValueChange = { textOne = it },
            label = { Text("Label") }
        )
        TextField(
            value = textTwo,
            onValueChange = { textTwo = it },
            label = { Text("Label") }
        )
        Button(onClick = {

        }) {
            Text(text = "Сохранить")
        }
    }
}