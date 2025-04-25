package com.github.projektmagma.magmaapp.home.domain.use_case.notebook

import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class SelectNotebookIdUseCase(
    private val notebookRepository: NotebookRepository
) {
    fun execute(id : String) = notebookRepository.selectNotebookById(id)
}