package com.github.projektmagma.magmaapp.core.domain.models

import com.github.projektmagma.magmaapp.core.data.models.GroupEntity

data class Group(
    val groupId: String,
    val groupSign: String,
    val classId: String,
    val qualificationId: String,
)

fun GroupEntity.toDomain(): Group {
    return Group(
        groupId = group_id.toString(),
        groupSign = group_sign,
        classId = class_id.toString(),
        qualificationId = qualification_id.toString(),
    )
}
