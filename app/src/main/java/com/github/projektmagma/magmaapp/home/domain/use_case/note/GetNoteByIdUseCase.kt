package com.github.projektmagma.magmaapp.home.domain.use_case.note

import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val noteRepository: NoteRepository
) {
    fun execute(id: String): Note = noteRepository.getNoteById(id)
}