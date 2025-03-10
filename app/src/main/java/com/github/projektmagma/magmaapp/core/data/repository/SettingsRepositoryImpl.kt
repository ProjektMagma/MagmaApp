package com.github.projektmagma.magmaapp.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.github.projektmagma.magmaapp.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.first

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    private companion object {
        val STAY_LOGIN = booleanPreferencesKey("stay_login")
        val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    override suspend fun setUserLogin(stayLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[STAY_LOGIN] = stayLogin
        }
    }

    override suspend fun setAppTheme(darkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE] = darkMode
        }
    }

    override suspend fun getUserLogin(): Boolean {
        return dataStore.data.first()[STAY_LOGIN] == true
    }

    override suspend fun getAppTheme(): Boolean {
        return dataStore.data.first()[DARK_MODE] == true
    }
}