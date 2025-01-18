package com.github.projektmagma.magmaapp.home.data.model

data class NoteDto(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)