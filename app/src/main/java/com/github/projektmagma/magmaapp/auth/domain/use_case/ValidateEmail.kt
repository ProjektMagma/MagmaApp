package com.github.projektmagma.magmaapp.auth.domain.use_case

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result

class ValidateEmail{

    fun execute(email: String): Result<Unit, EmailError> {
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