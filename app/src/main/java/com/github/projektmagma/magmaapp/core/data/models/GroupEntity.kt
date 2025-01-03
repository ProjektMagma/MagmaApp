package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Groups",
    foreignKeys = [
        ForeignKey(
            entity = QualificationEntity::class,
            parentColumns = ["qualification_id"],
            childColumns = ["qualification_id"],
        ),
        ForeignKey(
            entity = ClassesEntity::class,
            parentColumns = ["class_id"],
            childColumns = ["class_id"],
        )
    ]
)
data class GroupEntity(
    @PrimaryKey val group_id: Int,
    val group_sign: String,
    val class_id: Int,
    val qualification_id: Int,
)
