package com.github.projektmagma.magmaapp.home.presentation.settings_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAutoLogInUserUseCase
import kotlinx.coroutines.launch


class SettingsViewModel(
    val setAutoLogInUserUseCase: SetAutoLogInUserUseCase,
    val getAutoLogInUserUseCase: GetAutoLogInUserUseCase,
    val setAppThemeUseCase: SetAppThemeUseCase,
    val getAppThemeUseCase: GetAppThemeUseCase,
    val logoutUseCase: LogoutUseCase
) : ViewModel() {

    var autoLogInCheckbox by mutableStateOf(false)
    var darkModeSwitch by mutableStateOf(false)

    init {
        viewModelScope.launch {
            autoLogInCheckbox = getAutoLogInUserUseCase.execute()
            darkModeSwitch = getAppThemeUseCase.execute()
        }
    }

    fun saveSettings() {
        viewModelScope.launch {
            setAutoLogInUserUseCase.execute(autoLogInCheckbox)
            setAppThemeUseCase.execute(darkModeSwitch)
        }
    }

    fun logout() {
        viewModelScope.launch {
            setAutoLogInUserUseCase.execute(false) // Zapobiega zalogowaniu na nulla
            logoutUseCase.execute()
        }
    }
}