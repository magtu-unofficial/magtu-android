package ru.elerphore.magtu_android.data

import kotlinx.coroutines.flow.Flow

class SettingRepository(
    private val settingsDao: SettingsDao
) {
    fun setting(): Flow<List<Settings>> = settingsDao.settings()

    fun save(settings: Settings) = settingsDao.insert(settings)

}