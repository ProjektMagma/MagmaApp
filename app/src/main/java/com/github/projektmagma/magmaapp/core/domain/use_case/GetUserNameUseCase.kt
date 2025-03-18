package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.core.domain.repository.SettingsRepository

class GetUserNameUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend fun execute() = settingsRepository.getUserName()
}