package ru.elerphore.magtu_android.ui.screens.setting_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import ru.elerphore.magtu_android.data.DB
import ru.elerphore.magtu_android.data.Settings
import java.util.*
import kotlin.math.exp

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
        var groupName: String? by remember { mutableStateOf(null) }
        var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

        if(state.value.setting.isNotEmpty() && groupName == null) {
            groupName = state.value.setting.first().groupName!!
        }
        var expanded: Boolean by remember { mutableStateOf(false) }

        // Up Icon when expanded and down icon when collapsed
        val icon = if (expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        OutlinedTextField(
            value = groupName ?: "",
            singleLine = true,
            onValueChange = {
                expanded = true
                groupName = it
            },
            placeholder = { Text(text = "Название группы") },
            label = { Text("Введите вашу группу") },
            modifier =
                Modifier.fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
                Modifier
                    .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                    .height(200.dp),
            properties = PopupProperties(focusable = false)
        ) {
            state.value.groups.filter { it.lowercase().contains(groupName?.lowercase()!!) }.forEach { label ->
                DropdownMenuItem(onClick = {
                    groupName = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }

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