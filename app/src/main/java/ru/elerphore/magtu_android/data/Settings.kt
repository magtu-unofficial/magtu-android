package ru.elerphore.magtu_android.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Settings(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,

    @ColumnInfo(name = "group_name")
    val groupName: String? = null,

    @ColumnInfo(name = "is_teacher")
    val isTeacher: Boolean = false,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "surname")
    val surname: String? = null,
)