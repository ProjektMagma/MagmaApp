package com.github.projektmagma.magmaapp.core.domain.models

import com.github.projektmagma.magmaapp.core.data.models.UserEntity

data class User(
    val userId: String,
    val pesel: String,
    val firstName: String,
    val secondName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val roleId: String,
    val groupId: String
)

fun UserEntity.toDomain(): User {
    return User(
        userId = user_id.toString(),
        pesel = pesel,
        firstName = first_name,
        secondName = second_name,
        email = email.toString(),
        password = password.toString(),
        phoneNumber = phone_number.toString(),
        roleId = role_id.toString(),
        groupId = group_id.toString()
    )
}
