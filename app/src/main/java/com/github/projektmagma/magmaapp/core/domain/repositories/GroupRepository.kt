package com.github.projektmagma.magmaapp.core.domain.repositories

import com.github.projektmagma.magmaapp.core.domain.models.GroupWithClassAndQualification
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    fun getGroups(): Flow<List<GroupWithClassAndQualification>>
}