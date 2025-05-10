package com.github.projektmagma.magmaapp.home.presentation.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetUserNameUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetUserNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val setAutoLogInUserUseCase: SetAutoLogInUserUseCase,
    private val getAutoLogInUserUseCase: GetAutoLogInUserUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setUserNameUseCase: SetUserNameUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _autoLogInCheckbox = MutableStateFlow<Boolean>(false)
    val autoLogInCheckbox = _autoLogInCheckbox.asStateFlow()

    private val _darkModeSwitch = MutableStateFlow<Boolean>(false)
    val darkModeSwitch = _darkModeSwitch.asStateFlow()

    private val _displayNameTextbox = MutableStateFlow<String>("")
    val displayNameTextbox = _displayNameTextbox.asStateFlow()

    init {
        viewModelScope.launch {
            _displayNameTextbox.value = getUserNameUseCase.execute()
            _autoLogInCheckbox.value = getAutoLogInUserUseCase.execute()
            _darkModeSwitch.value = getAppThemeUseCase.execute()
        }
    }

    fun setDisplayNameTextbox(value: String) {
        _displayNameTextbox.value = value
    }

    fun setAutoLogInCheckbox(value: Boolean) {
        _autoLogInCheckbox.value = value
    }

    fun setDarkModeSwitch(value: Boolean) {
        _darkModeSwitch.value = value
    }

    fun saveSettings() {
        viewModelScope.launch {
            setAutoLogInUserUseCase.execute(_autoLogInCheckbox.value)
            setAppThemeUseCase.execute(_darkModeSwitch.value)
            setUserNameUseCase.execute(_displayNameTextbox.value)
        }
    }

    fun resetDisplayNameTextbox() {
        viewModelScope.launch {
            setUserNameUseCase.execute("")
            _displayNameTextbox.value = getUserNameUseCase.execute()
        }
    }

    fun logout() {
        viewModelScope.launch {
            setAutoLogInUserUseCase.execute(false) // Zapobiega zalogowaniu na nulla
            logoutUseCase.execute()
        }
    }
}