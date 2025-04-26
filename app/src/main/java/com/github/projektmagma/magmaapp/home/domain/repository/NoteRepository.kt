package com.github.projektmagma.magmaapp.home.domain.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import com.github.projektmagma.magmaapp.home.domain.model.Note

interface NoteRepository {
    suspend fun addNote(note: NoteDto): Result<Note, Error>
    suspend fun getAllNotes(): Result<SnapshotStateList<Note>, Error>
    suspend fun updateNote(note: Note): Result<Unit, Error>
    fun getNoteById(id: String): Note
    suspend fun removeNote(note: Note): Result<Unit, Error>
    
}