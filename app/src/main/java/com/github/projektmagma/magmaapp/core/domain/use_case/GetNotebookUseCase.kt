package com.github.projektmagma.magmaapp.core.domain.use_case

import com.github.projektmagma.magmaapp.core.domain.repository.NotebookRepository

class GetNotebookUseCase(
    private val notebookRepository: NotebookRepository
) {
    fun execute() = notebookRepository.getAllNotebooks()
    fun execute(index: Int) = notebookRepository.getNotebook(index)

}