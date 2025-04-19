package com.github.projektmagma.magmaapp.home.domain.use_case.note

import com.github.projektmagma.magmaapp.home.domain.repository.NoteRepository

class GetNotesUseCase(
    private val noteRepository: NoteRepository
) {
    suspend fun execute() = noteRepository.getAllNotes()
}