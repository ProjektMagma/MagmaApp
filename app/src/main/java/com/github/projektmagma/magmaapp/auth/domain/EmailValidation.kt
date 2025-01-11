package com.github.projektmagma.magmaapp.auth.domain

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result

class EmailValidation{

    fun validateEmail(email: String): Result<Unit, Error> {
        if (email.isEmpty()) {
            return Result.Error(EmailError.EMPTY)
        }

        if (!Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$").matches(email)) {
            return Result.Error(EmailError.INVALID_EMAIL)
        }

        return Result.Success(Unit)
    }

    enum class EmailError: Error {
        EMPTY,
        INVALID_EMAIL,
    }
}