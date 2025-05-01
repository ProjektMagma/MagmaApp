package com.github.projektmagma.magmaapp.home.presentation.settings_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetUserNameUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetUserNameUseCase
import kotlinx.coroutines.launch


class SettingsViewModel(
    val setAutoLogInUserUseCase: SetAutoLogInUserUseCase,
    val getAutoLogInUserUseCase: GetAutoLogInUserUseCase,
    val setAppThemeUseCase: SetAppThemeUseCase,
    val getAppThemeUseCase: GetAppThemeUseCase,
    val setUserNameUseCase: SetUserNameUseCase,
    val getUserNameUseCase: GetUserNameUseCase,
    val logoutUseCase: LogoutUseCase
) : ViewModel() {

    var autoLogInCheckbox by mutableStateOf(false)
    var darkModeSwitch by mutableStateOf(false)
    var displayNameTextbox by mutableStateOf("")

    init {
        viewModelScope.launch {
            displayNameTextbox = getUserNameUseCase.execute()
            autoLogInCheckbox = getAutoLogInUserUseCase.execute()
            darkModeSwitch = getAppThemeUseCase.execute()
        }
    }

    fun saveSettings() {
        viewModelScope.launch {
            setAutoLogInUserUseCase.execute(autoLogInCheckbox)
            setAppThemeUseCase.execute(darkModeSwitch)
            setUserNameUseCase.execute(displayNameTextbox)
        }
    }

    fun logout() {
        viewModelScope.launch {
            setAutoLogInUserUseCase.execute(false) // Zapobiega zalogowaniu na nulla
            logoutUseCase.execute()
        }
    }
}