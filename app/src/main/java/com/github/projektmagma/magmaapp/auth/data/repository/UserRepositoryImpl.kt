package com.github.projektmagma.magmaapp.auth.data.repository

import com.github.projektmagma.magmaapp.auth.data.DataError
import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val auth: FirebaseAuth
) : UserRepository {
    override suspend fun login(email: String, password: String): Result<FirebaseUser, Error> {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        val user = auth.currentUser

        return if (result.user != null) {
            Result.Success(user!!)
        } else {
            Result.Error(DataError.NetworkError.EMAIL_TAKEN)
        }
    }

    override suspend fun register(email: String, password: String): Result<FirebaseUser, Error> {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val user = auth.currentUser

        return if (result.user != null) {
            Result.Success(user!!)
        } else {
            Result.Error(DataError.NetworkError.SERVER_ERROR)
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }
}