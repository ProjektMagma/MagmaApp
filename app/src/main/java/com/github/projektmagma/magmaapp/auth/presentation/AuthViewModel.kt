package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.projektmagma.magmaapp.auth.domain.use_cases.LoginValidationUseCase

class AuthViewModel(
    private val loginValidationUseCase: LoginValidationUseCase
) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    fun isEmailAndPasswordValid(): Boolean {
        return loginValidationUseCase.execute(email.value, password.value)
    }
}