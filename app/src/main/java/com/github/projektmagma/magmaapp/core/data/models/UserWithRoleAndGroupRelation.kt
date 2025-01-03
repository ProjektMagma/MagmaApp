package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithRoleAndGroup(
    @Embedded val user: User,

    @Relation(
        parentColumn = "group_id",
        entityColumn = "group_id"
    )
    val group: Group,

    @Relation(
        parentColumn = "role_id",
        entityColumn = "role_id"
    )
    val userRoles: UserRoles,
)
