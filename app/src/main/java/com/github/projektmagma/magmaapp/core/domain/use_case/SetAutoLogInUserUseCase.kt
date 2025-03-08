package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository

class SetAutoLogInUserUseCase(
    private val loginPreferencesRepository: LoginPreferencesRepository
) {
    suspend fun execute(stayLogin: Boolean) = loginPreferencesRepository.saveUserLogin(stayLogin)
}