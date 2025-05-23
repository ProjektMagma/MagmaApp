package com.github.projektmagma.magmaapp.home.domain.use_case.notebook

import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class GetNotebookByIdUseCase(
    private val notebookRepository: NotebookRepository
) {
    fun execute(id: String): Notebook = notebookRepository.getNotebookById(id)
}