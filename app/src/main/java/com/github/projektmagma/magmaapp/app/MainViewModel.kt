package com.github.projektmagma.magmaapp.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_case.GetUserPreferencesUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Screen?>(null)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            if (!getUserPreferencesUseCase.execute()) {
                _startDestination.value = Screen.AuthGraph
                logoutUseCase.execute()
            } else {
                _startDestination.value = Screen.MainGraph
            }
        }
    }
}