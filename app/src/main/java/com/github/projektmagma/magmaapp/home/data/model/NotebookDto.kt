package com.github.projektmagma.magmaapp.home.data.model

import com.github.projektmagma.magmaapp.home.domain.model.Notebook

data class NotebookDto(
    val id: String = "",
    val title: String = "",
    val notes: MutableList<NoteDto> = mutableListOf()
)

fun Notebook.toDto(): NotebookDto {
    return NotebookDto(
        id = id,
        title = title.value,
        notes = notes.map { it.toDto() }.toMutableList()
    )
}
