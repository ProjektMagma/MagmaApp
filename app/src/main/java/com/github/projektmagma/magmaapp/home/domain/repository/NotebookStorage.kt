package com.github.projektmagma.magmaapp.home.domain.repository

import com.github.projektmagma.magmaapp.home.domain.model.Notebook

interface NotebookStorage {
    fun addNotebook(notebook: Notebook)
    fun addNotebooks(notebooks: List<Notebook>)
    fun getNotebooks(): List<Notebook>
    fun updateNotebook(notebook: Notebook)
    fun getNotebookById(id: String): Notebook
}