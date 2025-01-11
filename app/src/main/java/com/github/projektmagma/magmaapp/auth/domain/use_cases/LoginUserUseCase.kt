package com.github.projektmagma.magmaapp.auth.domain.use_cases

import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.auth.FirebaseUser

class LoginUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(email: String, password: String): Result<FirebaseUser, String> {
        return userRepository.login(email, password)
    }
}