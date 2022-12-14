package ru.elerphore.magtu_android.ui.screens.setting_screen

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.data.DB
import ru.elerphore.magtu_android.data.Settings
import java.util.*

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingScreen(
    viewModel: SettingScreenViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        GroupTextField(viewModel = viewModel)

        SubgroupTextField(viewModel = viewModel)

        Button(onClick = { CoroutineScope(IO).launch { DB.settingRepository.update(Settings(groupName = viewModel._state.groupName.value)) }
        }) { Text(text = "Сохранить") }
    }
}

@Composable
fun GroupTextField(viewModel: SettingScreenViewModel) {
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    var expanded: Boolean by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    TextField(
        value = viewModel._state.groupName.value,
        singleLine = true,
        onValueChange = {
            expanded = true
            viewModel._state.groupName.value = it
        },
        placeholder = { Text(text = "Название группы") },
        label = { Text("Группа") },
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates -> mTextFieldSize = coordinates.size.toSize() },
        trailingIcon = { Icon(icon,"contentDescription", Modifier.clickable { expanded = !expanded }) }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier =
        Modifier
            .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            .height(200.dp),
        properties = PopupProperties(focusable = false)
    ) {
        viewModel._state.groups.value.filter { it.lowercase().contains(viewModel._state.groupName.value.lowercase()) }.forEach { label ->
            DropdownMenuItem(onClick = { viewModel._state.groupName.value = label; expanded = false }) { Text(text = label) }
        }
    }
}

@Composable
fun SubgroupTextField(viewModel: SettingScreenViewModel) {
    val suggestions = listOf("1","2")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    var expanded: Boolean by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    TextField(
        value = viewModel._state.subGroup.value,
        onValueChange = {
            expanded = true
            viewModel._state.subGroup.value = it
        },
        modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates -> textfieldSize = coordinates.size.toSize() },
        label = {Text("Подгруппа")},
        trailingIcon = { Icon(icon,"contentDescription", Modifier.clickable { expanded = !expanded }) }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.width(with(LocalDensity.current){textfieldSize.width.toDp()})
    ) {
        suggestions.forEach { label ->
            DropdownMenuItem(onClick = {
                selectedText = label
            }) {
                Text(text = label)
            }
        }
    }
}