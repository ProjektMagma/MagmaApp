package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Groups")
data class Group(
    @PrimaryKey val group_id: Int?,
    val class_id: Int,
    val qualification_id: Int,
    val group_sign: String
)
