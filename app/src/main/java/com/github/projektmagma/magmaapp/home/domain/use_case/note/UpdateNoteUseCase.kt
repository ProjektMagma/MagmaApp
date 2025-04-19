package com.github.projektmagma.magmaapp.home.domain.use_case.note

import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.repository.NoteRepository

class UpdateNoteUseCase(
    private val noteRepository: NoteRepository,
) {
    suspend fun execute(note: Note) {
        noteRepository.updateNote(note)
    }
}