package ru.elerphore.magtu_android.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.elerphore.magtu_android.ui.screens.setting_screen.SettingScreenState

@Entity
data class Settings(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,

    @ColumnInfo(name = "group_name")
    val groupName: String,

    @ColumnInfo(name = "is_teacher")
    val isTeacher: Boolean = false,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "surname")
    val surname: String? = null,
)