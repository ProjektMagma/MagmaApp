package com.github.projektmagma.magmaapp.home.domain.use_case

import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class GetNotebookByIdUseCase(
    private val notebookRepository: NotebookRepository
) {
    fun execute(index: Int) = notebookRepository.getNotebookById(index)
}