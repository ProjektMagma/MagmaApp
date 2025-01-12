package com.github.projektmagma.magmaapp.auth.domain.use_case

import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.auth.FirebaseUser

class RegisterUserUseCase(
    private val userRepository: UserRepository,
) {
    suspend fun execute(email: String, password: String): Result<FirebaseUser, Error> {
        return userRepository.register(email, password)
    }
}