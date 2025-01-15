package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_case.LoginUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.RegisterUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateEmail
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidatePassword
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateRepeatedPassword
import com.github.projektmagma.magmaapp.auth.presentation.common.RegistrationType
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormEvent
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormState
import com.github.projektmagma.magmaapp.core.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    var passwordVisible = mutableStateOf(false)

    private val _authEventChannel = Channel<AuthEvent>()
    val validationEvent = _authEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegistrationFormEvent.Submit -> {
                submitData(event.registrationType)
            }
        }
    }

    private fun submitData(registrationType: RegistrationType) {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = if (registrationType == RegistrationType.REGISTER) {
            validateRepeatedPassword.execute(state.password, state.repeatedPassword)
        } else {
            Result.Success(Unit)
        }

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult
        ).any { it is Result.Error }

        if (hasError) {
            state = state.copy(
                emailError = if (emailResult is Result.Error) emailResult.error else null,
                passwordError = if (passwordResult is Result.Error) passwordResult.error else null,
                repeatedPasswordError = if (repeatedPasswordResult is Result.Error) repeatedPasswordResult.error else null
            )
            return
        }

        viewModelScope.launch {
            val result = when (registrationType) {
                RegistrationType.REGISTER -> registerUserUseCase.execute(state.email, state.password)
                RegistrationType.LOGIN -> loginUserUseCase.execute(state.email, state.password)
            }

            if (result is Result.Success) {
                _authEventChannel.send(AuthEvent.Success)
            } else {
                _authEventChannel.send(AuthEvent.Failure((result as Result.Error).error.messageId))
            }
        }
    }
        sealed class AuthEvent {
            data object Success : AuthEvent()
            data class Failure(val messageId: Int) : AuthEvent()
        }
    }