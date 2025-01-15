package com.github.projektmagma.magmaapp.auth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : LoginPreferencesRepository {

    private companion object {
        val STAY_LOGIN = booleanPreferencesKey("stay_login")
    }

    override suspend fun saveUserLogin(stayLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[STAY_LOGIN] = stayLogin
        }
    }

    override fun getUserLogin(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[STAY_LOGIN] ?: false
        }
    }
}