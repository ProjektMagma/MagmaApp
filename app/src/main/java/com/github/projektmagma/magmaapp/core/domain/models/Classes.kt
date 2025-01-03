package com.github.projektmagma.magmaapp.core.domain.models

import com.github.projektmagma.magmaapp.core.data.models.ClassesEntity

data class Classes(
    val classId: String,
    val name: String,
    val supervisingTeacherId: String
)

fun ClassesEntity.toDomain(): Classes{
    return Classes(
        classId = class_id.toString(),
        name = name,
        supervisingTeacherId = supervising_teacher_id.toString()
    )
}
