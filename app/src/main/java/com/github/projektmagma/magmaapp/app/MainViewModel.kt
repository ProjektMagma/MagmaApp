package com.github.projektmagma.magmaapp.app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAutoLogInUserUseCase: GetAutoLogInUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Screen?>(null)
    val startDestination = _startDestination.asStateFlow()
    private val _isAppInDarkMode = MutableStateFlow<Boolean>(false)
    val isAppInDarkMode = _isAppInDarkMode.asStateFlow()

    init {
        viewModelScope.launch {
            _isAppInDarkMode.value = getAppThemeUseCase.execute()
            if (!getAutoLogInUserUseCase.execute()) {
                _startDestination.value = Screen.AuthGraph
                logoutUseCase.execute()
            } else {
                _startDestination.value = Screen.MainGraph
            }
        }
    }
}