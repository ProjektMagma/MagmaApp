package com.github.projektmagma.magmaapp.core.domain.repositories

import com.github.projektmagma.magmaapp.core.domain.models.UserWithRoleAndGroup
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<UserWithRoleAndGroup>>
}