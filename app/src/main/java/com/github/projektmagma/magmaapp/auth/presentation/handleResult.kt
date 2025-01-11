package com.github.projektmagma.magmaapp.auth.presentation

import com.github.projektmagma.magmaapp.auth.presentation.model.UiState
import com.github.projektmagma.magmaapp.core.util.Result
import com.google.firebase.auth.FirebaseUser

fun handleResult(result: Result<FirebaseUser, String>): UiState {
    return when (result) {
        is Result.Success -> {
            UiState.Success(result.data)
        }
        is Result.Error -> {
            UiState.Error(result.error)
        }
    }
}