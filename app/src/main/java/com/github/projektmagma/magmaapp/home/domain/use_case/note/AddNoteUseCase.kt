package com.github.projektmagma.magmaapp.home.domain.use_case.note

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.repository.NoteRepository

class AddNoteUseCase(
    private val noteRepository: NoteRepository,
) {
    suspend fun execute(note: NoteDto): Result<Note, Error> {
        return noteRepository.addNote(note)
    }
}