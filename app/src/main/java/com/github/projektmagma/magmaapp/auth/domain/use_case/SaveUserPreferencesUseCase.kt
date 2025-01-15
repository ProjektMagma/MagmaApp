package com.github.projektmagma.magmaapp.auth.domain.use_case

import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository

public class SaveUserPreferencesUseCase(
    private val loginPreferencesRepository: LoginPreferencesRepository
) {
    suspend fun execute(stayLogin: Boolean) = loginPreferencesRepository.saveUserLogin(stayLogin)
}