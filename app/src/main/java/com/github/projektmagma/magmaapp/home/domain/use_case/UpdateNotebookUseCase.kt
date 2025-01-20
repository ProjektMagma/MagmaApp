package com.github.projektmagma.magmaapp.home.domain.use_case

import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class UpdateNotebookUseCase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(notebook: NotebookDto) = notebookRepository.updateNotebook(notebook)
}