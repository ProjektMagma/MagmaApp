package com.github.projektmagma.magmaapp.core.domain.repository

interface SettingsRepository {
    suspend fun setUserLogin(stayLogin: Boolean)
    suspend fun getUserLogin(): Boolean
    suspend fun setAppTheme(darkMode: Boolean)
    suspend fun getAppTheme(): Boolean
    suspend fun setUserName(userName: String)
    fun getUserName(): String
}