package ru.elerphore.magtu_android.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Settings(
    @PrimaryKey
    val uid: Int,

    @ColumnInfo(name = "group_name")
    val groupName: String,

    @ColumnInfo(name = "is_teacher")
    val isTeacher: Boolean
)