package com.github.projektmagma.magmaapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_case.LogoutUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class HomeViewModel(
    private val auth: FirebaseAuth,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    val user = auth.currentUser

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute()
        }
    }
}