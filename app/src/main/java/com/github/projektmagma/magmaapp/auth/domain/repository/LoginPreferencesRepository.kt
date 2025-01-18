package com.github.projektmagma.magmaapp.auth.domain.repository

interface LoginPreferencesRepository {
    suspend fun saveUserLogin(stayLogin: Boolean)
    suspend fun getUserLogin(): Boolean
}