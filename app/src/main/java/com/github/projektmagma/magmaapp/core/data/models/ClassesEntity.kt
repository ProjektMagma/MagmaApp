package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Classes",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["supervising_teacher_id"],
        )
    ]
)
data class ClassesEntity (
    @PrimaryKey val class_id: Int,
    val name: String,
    val supervising_teacher_id: Int
)
