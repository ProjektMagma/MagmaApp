package com.github.projektmagma.magmaapp.auth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository
import kotlinx.coroutines.flow.first

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

    override suspend fun getUserLogin(): Boolean {
        return dataStore.data.first()[STAY_LOGIN] == true
    }
}