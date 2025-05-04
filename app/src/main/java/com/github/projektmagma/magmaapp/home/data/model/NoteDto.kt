package com.github.projektmagma.magmaapp.home.data.model

import com.github.projektmagma.magmaapp.home.domain.model.Note

data class NoteDto(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val createdAt: Long = 0L,
)

fun Note.toDto(): NoteDto {
    return NoteDto(
        id = id,
        title = title.value,
        content = content.value,
        createdAt = createdAt
    )
}