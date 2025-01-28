package com.github.projektmagma.magmaapp.home.data.repository

import android.util.Log
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookStorage

class NoteStorageImpl : NotebookStorage {

    private val notesStorage = mutableListOf<Notebook>()

    override fun addNotebook(notebook: Notebook) {
        notesStorage.add(notebook)
    }

    override fun addNotebooks(notebooks: List<Notebook>) {
        notesStorage.addAll(notebooks)
    }

    override fun updateNotebook(notebook: Notebook) {
        val index = notesStorage.indexOfFirst { it.id == notebook.id }
        notesStorage[index] = notebook
    }

    override fun getNotebooks(): List<Notebook> = notesStorage

    override fun getNotebookById(id: String): Notebook {
        val index = notesStorage.indexOfFirst { it.id == id }
        return notesStorage[index]
    }
}