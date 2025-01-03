package com.github.projektmagma.magmaapp.core.domain.models

import com.github.projektmagma.magmaapp.core.data.models.GroupWithClassAndQualificationRelation

data class GroupWithClassAndQualification(
    val groupId: String,
    val groupSign: String,
    val classId: String,
    val qualificationId: String,
    val classes: Classes,
    val qualification: Qualification
)

fun GroupWithClassAndQualificationRelation.toDomain(): GroupWithClassAndQualification {
    return GroupWithClassAndQualification(
        groupId = group.group_id.toString(),
        groupSign = group.group_sign,
        classId = group.class_id.toString(),
        qualificationId = group.qualification_id.toString(),
        classes = classes.toDomain(),
        qualification = qualification.toDomain()
    )
}
