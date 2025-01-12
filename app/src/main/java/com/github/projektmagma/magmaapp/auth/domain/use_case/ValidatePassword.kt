package com.github.projektmagma.magmaapp.auth.domain.use_case

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result

class ValidatePassword {
    fun execute(password: String): Result<Unit, PasswordError> {
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

        if (!password.any() { it.isDigit() }) {
            return Result.Error(PasswordError.NO_DIGIT)
        }

        return Result.Success(Unit)
    }


    enum class PasswordError: Error {
        EMPTY,
        TOO_SHORT,
        NO_UPPERCASE,
        NO_LOWERCASE,
        NO_DIGIT
    }
}