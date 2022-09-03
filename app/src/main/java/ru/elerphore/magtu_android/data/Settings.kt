package ru.elerphore.magtu_android.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Settings(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 1,

    @ColumnInfo(name = "group_name")
    val groupName: String = "",

    @ColumnInfo(name = "sub_group")
    val subGroup: Int = 1,

    @ColumnInfo(name = "is_teacher")
    val isTeacher: Boolean = false,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "surname")
    val surname: String = "",
)