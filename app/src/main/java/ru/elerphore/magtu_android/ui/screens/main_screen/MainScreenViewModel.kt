package ru.elerphore.magtu_android.ui.screens.main_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.data.SchoolDay
import ru.elerphore.magtu_android.http_client.WC
import javax.inject.Inject

//@HiltViewModel
@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {

    val _schoolDays = mutableStateListOf<SchoolDay>()


    init {
        schoolDays()
    }

    private fun schoolDays() =
        viewModelScope.launch {
            try {
                val list = WC.getSchoolDays()
                _schoolDays.addAll(list.items)

            } catch(e: Throwable) {
                e.printStackTrace()
            }
        }

}