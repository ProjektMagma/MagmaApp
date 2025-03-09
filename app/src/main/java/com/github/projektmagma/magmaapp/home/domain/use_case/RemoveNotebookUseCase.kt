package com.github.projektmagma.magmaapp.home.domain.use_case

import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class RemoveNotebookUseCase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(notebook: Notebook) = notebookRepository.removeNotebook(notebook)
}