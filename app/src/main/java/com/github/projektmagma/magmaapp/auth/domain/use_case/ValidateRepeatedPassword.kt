package com.github.projektmagma.magmaapp.auth.domain.use_case

import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result

class ValidateRepeatedPassword {
    fun execute(password: String, repeatedPassword: String): Result<Unit, RepeatedPasswordError> {
        if (password != repeatedPassword) {
            return Result.Error(RepeatedPasswordError.PASSWORDS_DO_NOT_MATCH)
        }
        return Result.Success(Unit)
    }

    enum class RepeatedPasswordError(override val messageId: Int) : Error {
        PASSWORDS_DO_NOT_MATCH(R.string.error_passwords_do_not_match)
    }
}