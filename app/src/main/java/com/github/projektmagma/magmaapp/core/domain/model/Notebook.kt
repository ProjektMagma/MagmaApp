package com.github.projektmagma.magmaapp.core.domain.model

data class Notebook(
    val id: Int,
    val title: String,
    val notes: MutableList<Note> = mutableListOf()
)