package com.github.projektmagma.magmaapp.auth.domain.use_cases

import com.github.projektmagma.magmaapp.auth.domain.EmailValidation
import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.auth.FirebaseUser

class LoginUserUseCase(
    private val userRepository: UserRepository,
    private val emailValidation: EmailValidation
) {
    suspend fun execute(email: String, password: String): Result<FirebaseUser, Error> {
        val output = emailValidation.validateEmail(email)

        if (output is Result.Error) {
            return Result.Error(output.error)
        }

        return userRepository.login(email, password)
    }
}