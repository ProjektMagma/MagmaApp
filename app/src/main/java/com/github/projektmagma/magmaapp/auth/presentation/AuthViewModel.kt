package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_cases.LoginUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_cases.LoginValidationUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_cases.RegisterUserUseCase
import com.github.projektmagma.magmaapp.auth.presentation.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginValidationUseCase: LoginValidationUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state = _state.asStateFlow()

    fun login() {
        viewModelScope.launch {
            val result = loginUserUseCase.execute(email.value, password.value)
            _state.value = handleResult(result)
        }
    }

    fun register() {
        viewModelScope.launch {
            val result = registerUserUseCase.execute(email.value, password.value)
            _state.value = handleResult(result)
        }
    }

    fun isEmailAndPasswordValid(): Boolean {
        return loginValidationUseCase.execute(email.value, password.value)
    }
}