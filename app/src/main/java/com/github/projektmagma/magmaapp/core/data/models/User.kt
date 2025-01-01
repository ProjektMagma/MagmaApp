package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users",)
data class User(
    @PrimaryKey val pesel_id: Int?,
    val first_name: String,
    val second_name: String,
    val email: String?,
    val phone_number: String?,
    val role_id: Int,
    val group_id: Int?
)
