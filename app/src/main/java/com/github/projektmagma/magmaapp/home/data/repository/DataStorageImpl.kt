package com.github.projektmagma.magmaapp.home.data.repository

import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.DataStorage

class DataStorageImpl : DataStorage {
    
    private val _notebookStorage = mutableListOf<Notebook>()
    private var selectedNotebookIndex: Int = -1

    override fun addNotebook(notebook: Notebook) {
        _notebookStorage.add(notebook)
    }

    override fun addNotebooks(notebooks: List<Notebook>) {
        _notebookStorage.addAll(notebooks)
    }

    override fun updateNotebook(notebook: Notebook) {
        val index = _notebookStorage.indexOfFirst { it.id == notebook.id }
        _notebookStorage[index] = notebook
    }

    override fun getNotebooks(): List<Notebook> = _notebookStorage

    override fun getNotebookById(id: String): Notebook {
        val index = _notebookStorage.indexOfFirst { it.id == id }
        return _notebookStorage[index]
    }

    override fun removeNotebook(notebook: Notebook) {
        _notebookStorage.remove(notebook)
    }

    // DO WYBIERANIA NOTEBOOKA POTRZEBNE ABY BYŁY MOŻLIWE MODYFIKACJE NOTE - nie farmazon
    override fun selectNotebookId(id: String) {
        selectedNotebookIndex = _notebookStorage.indexOfFirst { it.id == id }
    }

    override fun getSelectedNotebook(): Notebook {
        return _notebookStorage[selectedNotebookIndex]
    }

    override fun addNote(note: Note) {
        _notebookStorage[selectedNotebookIndex].notes.add(note)
    }

    override fun addNotes(notes: List<Note>) {
        _notebookStorage[selectedNotebookIndex].notes.addAll(notes)
    }

    override fun getNotes(): List<Note> {
        return _notebookStorage[selectedNotebookIndex].notes
    }

    override fun updateNote(note: Note) {
        val index = _notebookStorage[selectedNotebookIndex].notes.indexOfFirst { it.id == note.id }
        _notebookStorage[selectedNotebookIndex].notes[index] = note
    }

    override fun getNoteById(id: String): Note {
        val index = _notebookStorage[selectedNotebookIndex].notes.indexOfFirst { it.id == id }
        return _notebookStorage[selectedNotebookIndex].notes[index]
    }

    override fun removeNote(note: Note) {
        _notebookStorage[selectedNotebookIndex].notes.remove(note)
    }
}