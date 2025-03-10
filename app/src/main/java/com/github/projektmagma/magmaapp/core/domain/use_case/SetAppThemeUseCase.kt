package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.core.domain.repository.SettingsRepository

class SetAppThemeUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend fun execute(darkMode: Boolean) = settingsRepository.setAppTheme(darkMode)
}