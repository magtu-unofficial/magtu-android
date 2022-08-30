package ru.elerphore.magtu_android.ui.screens.setting_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SettingScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var groupName by remember { mutableStateOf("") }

        TextField(
            value = groupName,
            singleLine = true,
            onValueChange = { groupName = it },
            placeholder = { Text(text = "Название группы") },
            label = { Text("Введите вашу группу") }
        )

        Button(onClick = {

        }) {
            Text(text = "Сохранить")
        }
    }
}