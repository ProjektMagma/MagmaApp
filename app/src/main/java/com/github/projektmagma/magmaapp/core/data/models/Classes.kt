package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Classes",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["supervising_teacher_id"],
        )
    ]
)
data class Classes (
    @PrimaryKey val class_id: Int?,
    val name: String,
    val supervising_teacher_id: Int?
)
