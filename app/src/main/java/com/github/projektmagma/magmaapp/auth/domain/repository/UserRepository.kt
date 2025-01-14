package com.github.projektmagma.magmaapp.auth.domain.repository

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun login(email: String, password: String): Result<FirebaseUser, Error>
    suspend fun register(email: String, password: String): Result<FirebaseUser, Error>
    suspend fun logout()
}