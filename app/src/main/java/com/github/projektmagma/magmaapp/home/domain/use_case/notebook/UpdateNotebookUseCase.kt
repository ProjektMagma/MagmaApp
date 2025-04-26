package com.github.projektmagma.magmaapp.home.domain.use_case.notebook

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class UpdateNotebookUseCase(
    private val notebookRepository: NotebookRepository,
) {
    suspend fun execute(notebook: Notebook): Result<Unit, Error> = notebookRepository.updateNotebook(notebook)
}