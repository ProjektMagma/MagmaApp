package com.github.projektmagma.magmaapp.home.data.model

import com.github.projektmagma.magmaapp.home.domain.model.Notebook

data class NotebookDto(
    val id: String = "",
    val title: String = "",
    val notes: MutableMap<String, NoteDto> = hashMapOf<String, NoteDto>(),
    val createdAt: Long = 0L,
    val lastModified: Long = 0L,
)

fun Notebook.toDto(): NotebookDto {
    val tmpMap = hashMapOf<String, NoteDto>()
    notes.forEach { note ->
        tmpMap[note.id] = note.toDto()
    }

    return NotebookDto(
        id = id,
        title = title.value,
        notes = tmpMap,
        createdAt = createdAt.longValue,
        lastModified = lastModified.longValue,
    )
}
