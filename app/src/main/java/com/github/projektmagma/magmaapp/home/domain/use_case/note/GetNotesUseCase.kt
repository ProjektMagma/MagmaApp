package com.github.projektmagma.magmaapp.home.domain.use_case.note

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.repository.NoteRepository

class GetNotesUseCase(
    private val noteRepository: NoteRepository
) {
    suspend fun execute(): Result<SnapshotStateList<Note>, Error> = noteRepository.getAllNotes()
}