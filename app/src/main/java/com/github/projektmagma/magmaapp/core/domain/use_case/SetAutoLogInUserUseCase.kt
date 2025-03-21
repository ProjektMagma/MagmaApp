package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.core.domain.repository.SettingsRepository

class SetAutoLogInUserUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend fun execute(stayLogin: Boolean) = settingsRepository.setUserLogin(stayLogin)
}