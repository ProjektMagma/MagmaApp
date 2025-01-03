package com.github.projektmagma.magmaapp.core.domain.models

import com.github.projektmagma.magmaapp.core.data.models.QualificationEntity

data class Qualification(
    val qualificationId: String,
    val name: String
)

fun QualificationEntity.toDomain(): Qualification {
    return Qualification(
        qualificationId = qualification_id.toString(),
        name = name
    )
}
