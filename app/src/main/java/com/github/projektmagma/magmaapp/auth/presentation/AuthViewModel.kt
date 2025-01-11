package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.EmailValidation
import com.github.projektmagma.magmaapp.auth.domain.use_cases.LoginUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_cases.RegisterUserUseCase
import com.github.projektmagma.magmaapp.auth.presentation.model.UiState
import com.github.projektmagma.magmaapp.core.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state = _state.asStateFlow()

    fun login() {
        viewModelScope.launch {
            when (val result = loginUserUseCase.execute(email.value, password.value)) {
                is Result.Success -> {
                    _state.value = UiState.Success(result.data)
                }
                is Result.Error -> {
                    _state.value = when (result.error) {
                        EmailValidation.EmailError.EMPTY -> UiState.Error("Email is empty")
                        EmailValidation.EmailError.INVALID_EMAIL -> UiState.Error("Email is invalid")
                        else -> UiState.Error("An error occurred")
                    }
                }
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            val result = registerUserUseCase.execute(email.value, password.value)
            _state.value = when (result) {
                is Result.Success -> {
                    UiState.Success(result.data)
                }
                is Result.Error -> {
                    when (result.error) {
                        EmailValidation.EmailError.EMPTY -> UiState.Error("Email is empty")
                        EmailValidation.EmailError.INVALID_EMAIL -> UiState.Error("Email is invalid")
                        else -> UiState.Error("An error occurred")
                    }
                }
            }
        }
    }
}