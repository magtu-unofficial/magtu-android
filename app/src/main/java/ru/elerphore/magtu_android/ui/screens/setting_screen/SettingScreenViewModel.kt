package ru.elerphore.magtu_android.ui.screens.setting_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.data.DB
import ru.elerphore.magtu_android.data.SettingRepository
import ru.elerphore.magtu_android.data.Settings
import ru.elerphore.magtu_android.http_client.WC

class SettingScreenViewModel(
    private val categoryRepository: SettingRepository = DB.settingRepository
)  : ViewModel() {

   var _state by mutableStateOf(SettingScreenState())

    init {
        CoroutineScope(IO).launch {
            categoryRepository.setting().let { setting ->
                val _setting = (when(setting) {
                    null -> categoryRepository.save(Settings()).let { Settings() }
                    else -> setting
                })
                _state = SettingScreenState().apply {
                    this.groupName.value = _setting.groupName
                    this.subGroup.value = _setting.subGroup.toString()
                    this.groups.value = WC.getGroups()
                }

            }
        }
    }
}

data class SettingScreenState(
    var groupName: MutableState<String> = mutableStateOf(""),
    var subGroup: MutableState<String> = mutableStateOf(""),
    val groups: MutableState<List<String>> = mutableStateOf(emptyList<String>())
)