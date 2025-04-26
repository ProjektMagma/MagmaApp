package com.github.projektmagma.magmaapp.home.domain.use_case.note

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.repository.NoteRepository

class RemoveNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend fun execute(note: Note): Result<Unit, Error> = noteRepository.removeNote(note)
}