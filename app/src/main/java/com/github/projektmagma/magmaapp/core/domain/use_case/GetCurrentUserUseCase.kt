package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository

class GetCurrentUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute() = userRepository.getCurrentUser()
}