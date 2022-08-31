package ru.elerphore.magtu_android.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SettingsDao {
    @Query("SELECT * FROM settings")
    abstract fun settings(): Flow<List<Settings>>

    @Insert
    abstract suspend fun insertAll(vararg users: Settings)

    @Insert
    abstract fun insert(settings: Settings)

    @Delete
    abstract suspend fun delete(settings: Settings)

    @Update
    abstract suspend fun update(settings: Settings)
}