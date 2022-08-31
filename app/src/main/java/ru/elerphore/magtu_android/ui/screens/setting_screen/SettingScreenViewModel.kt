package ru.elerphore.magtu_android.ui.screens.setting_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.data.DB
import ru.elerphore.magtu_android.data.SettingRepository
import ru.elerphore.magtu_android.data.Settings
import ru.elerphore.magtu_android.http_client.WC
import javax.inject.Inject

class SettingScreenViewModel(
    private val categoryRepository: SettingRepository = DB.settingRepository
)  : ViewModel() {

    private val _state = MutableStateFlow(SettingScreenState())
    val state: StateFlow<SettingScreenState>
        get() = _state

    init {
        viewModelScope.launch {

            val groups = WC.getGroups()

            combine(categoryRepository.setting()) { _settings ->
                SettingScreenState(
                    setting = _settings.first(),
                    groups = groups
                )
            }.collect {
                _state.value = it
            }

        }
    }
}

data class SettingScreenState(
    val setting: List<Settings> = emptyList(),
    val groups: List<String> = emptyList()
)