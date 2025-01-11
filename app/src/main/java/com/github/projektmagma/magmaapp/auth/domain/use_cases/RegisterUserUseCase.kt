package com.github.projektmagma.magmaapp.auth.domain.use_cases

import com.github.projektmagma.magmaapp.auth.domain.EmailValidation
import com.github.projektmagma.magmaapp.auth.domain.PasswordValidation
import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.auth.FirebaseUser

class RegisterUserUseCase(
    private val userRepository: UserRepository,
    private val emailValidation: EmailValidation,
    private val passwordValidation: PasswordValidation
) {
    suspend fun execute(email: String, password: String): Result<FirebaseUser, Error> {
        val emailOutput = emailValidation.validateEmail(email)

        if (emailOutput is Result.Error) {
            return Result.Error(emailOutput.error)
        }

        val passwordOutput = passwordValidation.validatePassword(password)
        if (passwordOutput is Result.Error) {
            return Result.Error(passwordOutput.error)
        }

        return userRepository.register(email, password)
    }
}