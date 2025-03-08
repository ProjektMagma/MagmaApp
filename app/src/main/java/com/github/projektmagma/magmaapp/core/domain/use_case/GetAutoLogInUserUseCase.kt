package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository

class GetAutoLogInUserUseCase(
    private val loginPreferencesRepository: LoginPreferencesRepository
) {
    suspend fun execute() = loginPreferencesRepository.getUserLogin()
}