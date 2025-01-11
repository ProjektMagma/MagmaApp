package com.github.projektmagma.magmaapp.auth.domain.use_cases

import com.github.projektmagma.magmaapp.auth.domain.Validation

class LoginValidationUseCase(
    private val validLoginChecker: Validation
) {
    fun execute(email: String, password: String): Boolean {
        return !validLoginChecker.isNullOrEmpty(
            email,
            password
        ) && validLoginChecker.testRegex(email, Regex("^\\S+@\\S+\\.\\S+\$"))
    }
}