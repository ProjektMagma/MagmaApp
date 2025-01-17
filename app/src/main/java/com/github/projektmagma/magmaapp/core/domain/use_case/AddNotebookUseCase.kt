package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.core.domain.model.Notebook
import com.github.projektmagma.magmaapp.core.domain.repository.NotebookRepository

class AddNotebookUseCase(
    private val notebookRepository: NotebookRepository
) {
    fun execute(notebook: Notebook) = notebookRepository.addNotebook(notebook)
}