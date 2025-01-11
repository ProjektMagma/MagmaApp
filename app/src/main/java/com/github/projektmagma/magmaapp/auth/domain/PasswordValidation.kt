package com.github.projektmagma.magmaapp.auth.domain

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result

class PasswordValidation {
    fun validatePassword(password: String): Result<Unit, PasswordError> {
        if (password.isEmpty()) {
            return Result.Error(PasswordError.EMPTY)
        }

        if (password.length < 6) {
            return Result.Error(PasswordError.TOO_SHORT)
        }

        if (!password.any { it.isUpperCase() }) {
            return Result.Error(PasswordError.NO_UPPERCASE)
        }

        if (!password.any { it.isLowerCase() }) {
            return Result.Error(PasswordError.NO_LOWERCASE)
        }

        return Result.Success(Unit)
    }


    enum class PasswordError: Error {
        EMPTY,
        TOO_SHORT,
        NO_UPPERCASE,
        NO_LOWERCASE,
    }
}