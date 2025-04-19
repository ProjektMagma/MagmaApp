package com.github.projektmagma.magmaapp.home.data.repository

import androidx.compose.runtime.mutableStateOf
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.DataStorage

class DataStorageImpl : DataStorage {

    private val notebookStorage = mutableListOf<Notebook>()
    private val selectedNotebook = mutableStateOf<Notebook?>(null)

    override fun addNotebook(notebook: Notebook) {
        notebookStorage.add(notebook)
    }

    override fun addNotebooks(notebooks: List<Notebook>) {
        notebookStorage.addAll(notebooks)
    }

    override fun updateNotebook(notebook: Notebook) {
        val index = notebookStorage.indexOfFirst { it.id == notebook.id }
        notebookStorage[index] = notebook
    }

    override fun getNotebooks(): List<Notebook> = notebookStorage

    override fun getNotebookById(id: String): Notebook {
        val index = notebookStorage.indexOfFirst { it.id == id }
        return notebookStorage[index]
    }

    override fun removeNotebook(notebook: Notebook) {
        notebookStorage.remove(notebook)
    }

    // DO WYBIERANIA NOTEBOOKA POTRZEBNE ABY BYŁY MOŻLIWE MODYFIKACJE NOTE
    override fun selectNotebookById(id: String) {
        selectedNotebook.value = getNotebookById(id)
    }

    override fun addNote(note: Note) {
        selectedNotebook.value?.notes?.add(note)
    }

    override fun addNotes(notes: List<Note>) {
        selectedNotebook.value?.notes?.addAll(notes)
    }

    override fun getNotes(): List<Note> {
        return selectedNotebook.value?.notes ?: emptyList()
    }

    override fun updateNote(note: Note) {
        val index = selectedNotebook.value?.notes?.indexOfFirst { it.id == note.id } ?: return
        selectedNotebook.value?.notes[index] = note
    }

    override fun getNoteById(id: String): Note {
        val index = selectedNotebook.value?.notes?.indexOfFirst { it.id == id } ?: return Note()
        return selectedNotebook.value?.notes[index] ?: Note()
    }

    override fun removeNote(note: Note) {
        selectedNotebook.value?.notes?.remove(note)
    }
}