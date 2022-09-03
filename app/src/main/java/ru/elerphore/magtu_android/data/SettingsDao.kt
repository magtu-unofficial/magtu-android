package ru.elerphore.magtu_android.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SettingsDao {
    @Query("SELECT * FROM settings where uid = 1")
    abstract fun settings(): Settings?

    @Insert
    abstract suspend fun insertAll(vararg users: Settings)

    @Insert
    abstract fun insert(settings: Settings)

    @Delete
    abstract fun delete(settings: Settings)

    @Update
    abstract fun update(settings: Settings)
}