package com.github.projektmagma.magmaapp.home.domain.use_case

import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.model.toDomain
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookStorage

class AddNotebookUseCase(
    private val notebookRepository: NotebookRepository,
) {
    suspend fun execute(notebook: NotebookDto): Result<Notebook, Error> {
        return notebookRepository.addNotebook(notebook)
    }
}