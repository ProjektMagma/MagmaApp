package com.github.projektmagma.magmaapp.home.domain.use_case.notebook

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class GetNotebooksUseCase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(): Result<SnapshotStateList<Notebook>, Error> = notebookRepository.getAllNotebooks()
}