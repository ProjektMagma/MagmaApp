package com.github.projektmagma.magmaapp.auth.data

import com.github.projektmagma.magmaapp.auth.domain.Validation

class LoginValidation(
) : Validation {

    override fun isNullOrEmpty(email: String, password: String): Boolean {
        return (email.isEmpty() || password.isEmpty()) || (email.isBlank() || password.isBlank())
    }

    override fun testRegex(email: String, regex: Regex): Boolean {
        return regex.matches(email)
    }
}