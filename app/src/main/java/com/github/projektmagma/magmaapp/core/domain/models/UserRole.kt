package com.github.projektmagma.magmaapp.core.domain.models

import com.github.projektmagma.magmaapp.core.data.models.UserRolesEntity

data class UserRole(
    val roleId: String,
    val name: String
)

fun UserRolesEntity.toDomain(): UserRole{
    return UserRole(
        roleId = role_id.toString(),
        name = name
    )
}
