package com.github.projektmagma.magmaapp.auth.presentation.model

import com.github.projektmagma.magmaapp.auth.presentation.common.RegistrationType

sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatedPasswordChanged(
        val repeatedPassword: String
    ) : RegistrationFormEvent()

    data class Submit(val registrationType: RegistrationType) : RegistrationFormEvent()
}