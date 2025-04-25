package com.github.projektmagma.magmaapp.home.domain.repository

import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook

interface DataStorage {
    // Można to potem rozdzielić na różne storage
    // (Znane wcześniej jako NotebookStorage)

    // Notebook
    fun addNotebook(notebook: Notebook)
    fun addNotebooks(notebooks: List<Notebook>)
    fun getNotebooks(): List<Notebook>
    fun updateNotebook(notebook: Notebook)
    fun getNotebookById(id: String): Notebook
    fun removeNotebook(notebook: Notebook)
    fun selectNotebookId(id: String) 
    fun getSelectedNotebook(): Notebook

    // Note
    fun addNote(note: Note)
    fun addNotes(notes: List<Note>)
    fun getNotes(): List<Note>
    fun updateNote(note: Note)
    fun getNoteById(id: String): Note
    fun removeNote(note: Note)
}