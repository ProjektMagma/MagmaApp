package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Qualifications")
data class QualificationEntity(
    @PrimaryKey val qualification_id: Int,
    val name: String
)
