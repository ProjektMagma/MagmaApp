package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserRoles")
data class UserRolesEntity(
    @PrimaryKey val role_id: Int,
    val name: String
)