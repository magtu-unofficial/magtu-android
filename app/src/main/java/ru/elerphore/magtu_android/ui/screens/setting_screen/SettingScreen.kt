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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.data.DB
import ru.elerphore.magtu_android.data.Settings

@Composable
fun SettingScreen(
    viewModel: SettingScreenViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var groupName by remember { mutableStateOf("") }

//        if(viewModel.setting != null) {
//            groupName = viewModel.setting!!.groupName!!
//        }

        if(state.value.setting.isNotEmpty()) {
            groupName = state.value.setting.first().groupName!!
        }

        TextField(
            value = groupName,
            singleLine = true,
            onValueChange = { groupName = it },
            placeholder = { Text(text = "Название группы") },
            label = { Text("Введите вашу группу") }
        )

        Button(onClick = {
            CoroutineScope(IO).launch {
                val st = Settings(groupName = groupName)
                DB.settingRepository.save(st)
            }
        }) {
            Text(text = "Сохранить")
        }
    }
}