package com.github.projektmagma.magmaapp.home.data.model

data class NotebookDto(
    val id: Int = 0,
    val title: String = "",
    val notes: MutableList<NoteDto> = mutableListOf()
)
