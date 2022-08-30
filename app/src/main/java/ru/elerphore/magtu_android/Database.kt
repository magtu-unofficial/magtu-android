package ru.elerphore.magtu_android

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.elerphore.magtu_android.data.Settings
import ru.elerphore.magtu_android.data.SettingsDao

@Database(entities = [Settings::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}