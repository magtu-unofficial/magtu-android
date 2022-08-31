package ru.elerphore.magtu_android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Settings::class], version = 1)
abstract class MagtuDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}

object DB {
    lateinit var db: MagtuDatabase

    lateinit var appContext: Context

    fun provide(context: Context) {
        appContext = context
        db = Room.databaseBuilder(context, MagtuDatabase::class.java, "magtu.db").build()
    }

    val settingRepository by lazy {
        SettingRepository(
            settingsDao = db.settingsDao()
        )
    }

}