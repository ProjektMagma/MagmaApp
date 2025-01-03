package com.github.projektmagma.magmaapp.core.data.repository

import com.github.projektmagma.magmaapp.core.data.dao.GroupDao
import com.github.projektmagma.magmaapp.core.domain.models.GroupWithClassAndQualification
import com.github.projektmagma.magmaapp.core.domain.models.toDomain
import com.github.projektmagma.magmaapp.core.domain.repositories.GroupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GroupRepositoryImpl(
    private val groupDao: GroupDao
): GroupRepository {
    override fun getGroups(): Flow<List<GroupWithClassAndQualification>> = flow {
        val groupList = groupDao.getGroups().map { it.toDomain() }
        emit(groupList)
    }.flowOn(Dispatchers.IO)
}