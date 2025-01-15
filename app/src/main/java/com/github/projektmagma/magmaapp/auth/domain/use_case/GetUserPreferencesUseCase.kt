package com.github.projektmagma.magmaapp.auth.domain.use_case

import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository

public class GetUserPreferencesUseCase(
    private val loginPreferencesRepository: LoginPreferencesRepository
) {
    fun execute() = loginPreferencesRepository.getUserLogin()
}