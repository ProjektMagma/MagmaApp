package com.github.projektmagma.magmaapp.home.domain.repository

interface NoteRepository {
    suspend fun addNote()
    suspend fun updateNote()
}