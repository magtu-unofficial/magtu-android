package ru.elerphore.magtu_android.data

import androidx.room.*

@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings")
    fun getAll(): List<Settings>

    @Insert
    fun insertAll(vararg users: Settings)

    @Insert
    fun insert(settings: Settings)

    @Delete
    fun delete(settings: Settings)

    @Update
    fun update(settings: Settings)
}