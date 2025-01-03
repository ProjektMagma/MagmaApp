package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Users",
    foreignKeys = [
        ForeignKey(
            entity = UserRoles::class,
            parentColumns = ["role_id"],
            childColumns = ["role_id"]
        ),
        ForeignKey(
            entity = Group::class,
            parentColumns = ["group_id"],
            childColumns = ["group_id"]
        )
    ]
)
data class User(
    @PrimaryKey val user_id: Int,
    val pesel: String,
    val first_name: String,
    val second_name: String,
    val email: String?,
    val password: String?,
    val phone_number: String?,
    val role_id: Int,
    val group_id: Int
)
