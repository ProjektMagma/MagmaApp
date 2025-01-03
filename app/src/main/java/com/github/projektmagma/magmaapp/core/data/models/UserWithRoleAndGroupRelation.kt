package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithRoleAndGroupRelation(
    @Embedded val userEntity: UserEntity,

    @Relation(
        parentColumn = "group_id",
        entityColumn = "group_id"
    )
    val group: GroupEntity,

    @Relation(
        parentColumn = "role_id",
        entityColumn = "role_id"
    )
    val userRoles: UserRolesEntity,
)
