package com.github.projektmagma.magmaapp.home.data.repository

import com.github.projektmagma.magmaapp.home.data.NotebookDao
import com.github.projektmagma.magmaapp.home.data.model.toDomain
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.model.toEntity
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookStorage
import com.github.projektmagma.magmaapp.home.domain.repository.Notebooks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotebookStorageImpl(
    private val notebookDao: NotebookDao
) : NotebookStorage {

    private val notesStorage = mutableListOf<Notebook>()

    override fun addNotebook(notebook: Notebook) {
        notesStorage.add(notebook)
        notebookDao.upsertNotebook(notebook.toEntity())
    }

    override fun addNotebooks(notebooks: List<Notebook>) {
        notesStorage.addAll(notebooks)
    }

    override fun updateNotebook(notebook: Notebook) {
        val index = notesStorage.indexOfFirst { it.id == notebook.id }
        notebookDao.upsertNotebook(notebook.toEntity())
        notesStorage[index] = notebook
    }

    override fun getNotebooks(): Flow<List<Notebook>> = flow {
        emit(notebookDao.getNotebooks().map { it.toDomain() })
    }

    override fun getNotebookById(id: String): Notebook {
        val index = notesStorage.indexOfFirst { it.id == id }
        return notesStorage[index]
    }
}