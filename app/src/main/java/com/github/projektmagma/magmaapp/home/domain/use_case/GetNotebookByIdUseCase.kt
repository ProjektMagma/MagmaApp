package com.github.projektmagma.magmaapp.home.domain.use_case

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository

class GetNotebookByIdUseCase(
    private val notebookRepository: NotebookRepository
) {
    var lastNotebookIndex by mutableIntStateOf(0)

    fun execute(index: Int) : Notebook {
        lastNotebookIndex = index
        return notebookRepository.getNotebookById(index)
    }

    fun execute() = lastNotebookIndex
}