package com.github.projektmagma.magmaapp.core.data.repository

import com.github.projektmagma.magmaapp.core.domain.model.Note
import com.github.projektmagma.magmaapp.core.domain.repositories.NoteRepository

class NoteRepositoryImpl: NoteRepository {
    override suspend fun getNotes(): List<Note> {
        TODO("Not yet implemented")
    }

    override suspend fun getNoteById(id: Int): Note {
        TODO("Not yet implemented")
    }

    override suspend fun addNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoteById(id: Int) {
        TODO("Not yet implemented")
    }
}