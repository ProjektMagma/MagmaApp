package com.github.projektmagma.magmaapp.auth.data

import com.github.projektmagma.magmaapp.auth.domain.Validation
import com.github.projektmagma.magmaapp.core.util.Error

class EmailValidation(
) : Validation {

    override fun isNullOrEmpty(email: String, password: String): Boolean {
        return (email.isEmpty() || password.isEmpty()) || (email.isBlank() || password.isBlank())
    }

    override fun testRegex(email: String, regex: Regex): Boolean {
        return regex.matches(email)
    }

    enum class EmailError: Error {
        EMPTY_EMAIL,
        INVALID_EMAIL,
        TAKEN_EMAIL
    }
}