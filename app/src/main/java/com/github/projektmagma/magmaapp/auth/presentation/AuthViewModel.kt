package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_cases.EncryptUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val encryptionUseCase: EncryptUseCase
): ViewModel() {

    val password = mutableStateOf("")

    private val _encryptedPassword = MutableStateFlow("")
    val encryptedPassword= _encryptedPassword.asStateFlow()

    fun encryptPassword(password: String) {
        viewModelScope.launch {
            _encryptedPassword.value = encryptionUseCase.execute(password)
        }
    }
}