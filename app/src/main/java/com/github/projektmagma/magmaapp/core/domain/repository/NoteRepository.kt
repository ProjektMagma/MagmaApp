package com.github.projektmagma.magmaapp.core.domain.repository

import com.github.projektmagma.magmaapp.core.domain.model.Note

interface NoteRepository {
    suspend fun getNotes(): List<Note>
    suspend fun getNoteById(id: Int): Note
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNoteById(id: Int)
}