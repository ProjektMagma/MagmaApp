package com.github.projektmagma.magmaapp.auth.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginPreferencesRepository {
    suspend fun saveUserLogin(stayLogin: Boolean)
    fun getUserLogin(): Flow<Boolean>
}