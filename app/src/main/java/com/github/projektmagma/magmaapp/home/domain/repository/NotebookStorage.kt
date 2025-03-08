package com.github.projektmagma.magmaapp.home.domain.repository

import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import kotlinx.coroutines.flow.Flow

typealias Notebooks = List<Notebook>

interface NotebookStorage {
    fun addNotebook(notebook: Notebook)
    fun addNotebooks(notebooks: Notebooks)
    fun getNotebooks(): Flow<Notebooks>
    fun updateNotebook(notebook: Notebook)
    fun getNotebookById(id: String): Notebook
}