package com.github.projektmagma.magmaapp.auth.domain.use_case

import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result

class ValidateEmail {

    fun execute(email: String): Result<Unit, EmailError> {
        if (email.isEmpty()) {
            return Result.Error(EmailError.EMPTY)
        }

        if (!Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$").matches(email)) {
            return Result.Error(EmailError.INVALID_EMAIL)
        }

        return Result.Success(Unit)
    }

    enum class EmailError(override val messageId: Int) : Error {
        EMPTY(R.string.error_empty_email),
        INVALID_EMAIL(R.string.error_invalid_email)
    }
}