package com.github.projektmagma.magmaapp.auth.presentation.model

import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateEmail.EmailError
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidatePassword.PasswordError
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateRepeatedPassword.RepeatedPasswordError

data class RegistrationFormState(
    val email: String = "",
    val emailError: EmailError? =  null,
    val password: String = "",
    val passwordError: PasswordError? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: RepeatedPasswordError? = null,
)
