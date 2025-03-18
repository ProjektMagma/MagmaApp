package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.core.domain.repository.SettingsRepository

class SetUserNameUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend fun execute(userName: String) = settingsRepository.setUserName(userName)
}