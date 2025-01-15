package com.github.projektmagma.magmaapp.auth.data.repository

import com.github.projektmagma.magmaapp.auth.data.DataError
import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val auth: FirebaseAuth
) : UserRepository {
    override suspend fun login(email: String, password: String): Result<FirebaseUser, Error> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = auth.currentUser

            return if (result.user != null) {
                Result.Success(user!!)
            } else {
                Result.Error(DataError.NetworkError.SERVER_ERROR)
            }
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.Error(DataError.NetworkError.INVALID_CREDENTIALS)
        } catch (e: Exception) {
            Result.Error(DataError.NetworkError.UNKNOWN_ERROR)
        }
    }

    override suspend fun register(email: String, password: String): Result<FirebaseUser, Error> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user

            return if (user != null) {
                Result.Success(user)
            } else {
                Result.Error(DataError.NetworkError.SERVER_ERROR)
            }
        } catch (e: FirebaseAuthUserCollisionException) {
            Result.Error(DataError.NetworkError.EMAIL_TAKEN)
        }
        catch (e: Exception) {
            Result.Error(DataError.NetworkError.UNKNOWN_ERROR)
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }
}