package com.github.projektmagma.magmaapp.auth.domain.use_case

import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository

class GetUserPreferencesUseCase(
    private val loginPreferencesRepository: LoginPreferencesRepository
) {
    suspend fun execute() = loginPreferencesRepository.getUserLogin()
}