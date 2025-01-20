package com.github.projektmagma.magmaapp.home.domain.use_case

import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class GetNotebooksUseCase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(userId: String) = notebookRepository.getAllNotebooks(userId)
}