package com.github.projektmagma.magmaapp.core.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class GroupWithClassAndQualificationEntity(
    @Embedded val group: GroupEntity,

    @Relation(
        parentColumn = "class_id",
        entityColumn = "class_id"
    )
    val classes: ClassesEntity,

    @Relation(
        parentColumn = "qualification_id",
        entityColumn = "qualification_id"
    )
    val qualification: QualificationEntity
)
