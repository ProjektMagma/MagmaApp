package com.github.projektmagma.magmaapp.auth.presentation.model

import com.google.firebase.auth.FirebaseUser

sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: FirebaseUser) : UiState()
    data class Error(val error: String) : UiState()
}