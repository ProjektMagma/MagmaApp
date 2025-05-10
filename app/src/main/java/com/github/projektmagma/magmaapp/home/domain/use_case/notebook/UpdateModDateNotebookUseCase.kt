package com.github.projektmagma.magmaapp.home.domain.use_case.notebook

import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class UpdateModDateNotebookUseCase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(notebook: Notebook) = notebookRepository.updateModDateNotebook(notebook)
}