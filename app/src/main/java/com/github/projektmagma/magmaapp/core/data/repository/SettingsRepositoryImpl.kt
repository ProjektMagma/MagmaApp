package com.github.projektmagma.magmaapp.core.data.repository

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.github.projektmagma.magmaapp.core.domain.repository.SettingsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.first

class SettingsRepositoryImpl(
    private val auth: FirebaseAuth,
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    private val localDisplayName = mutableStateOf(auth.currentUser?.displayName ?: "")

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

    override suspend fun setUserName(userName: String) {
        if (userName.isBlank()) {
            localDisplayName.value = auth.currentUser!!.email!!
            return
        }

        auth.currentUser!!.updateProfile(
            UserProfileChangeRequest.Builder()
                .setDisplayName(userName)
                .build()
        )

        localDisplayName.value = userName
    }

    override suspend fun getUserName(): String {
        return localDisplayName.value 
    }
}