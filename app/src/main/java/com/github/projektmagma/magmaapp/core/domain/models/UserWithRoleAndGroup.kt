package com.github.projektmagma.magmaapp.core.domain.models

import com.github.projektmagma.magmaapp.core.data.models.UserWithRoleAndGroupRelation

data class UserWithRoleAndGroup(
    val userId: String,
    val pesel: String,
    val firstName: String,
    val secondName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val roleId: String,
    val groupId: String,
    val group: Group,
    val userRoles: UserRole
)

fun UserWithRoleAndGroupRelation.toDomain(): UserWithRoleAndGroup{
    return UserWithRoleAndGroup(
        userId = userEntity.user_id.toString(),
        pesel = userEntity.pesel,
        firstName = userEntity.first_name,
        secondName = userEntity.second_name,
        email = userEntity.email.toString(),
        password = userEntity.password.toString(),
        phoneNumber = userEntity.phone_number.toString(),
        roleId = userEntity.role_id.toString(),
        groupId = userEntity.group_id.toString(),
        group = group.toDomain(),
        userRoles = userRoles.toDomain()
    )
}
