package com.github.projektmagma.magmaapp.core.data.repository

import com.github.projektmagma.magmaapp.core.data.dao.UserDao
import com.github.projektmagma.magmaapp.core.domain.models.UserWithRoleAndGroup
import com.github.projektmagma.magmaapp.core.domain.models.toDomain
import com.github.projektmagma.magmaapp.core.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val userDao: UserDao
): UserRepository {
    override fun getUsers(): Flow<List<UserWithRoleAndGroup>> = flow {
        val userList = userDao.getUsers().map { it.toDomain() }
        emit(userList)
    }.flowOn(Dispatchers.IO)
}