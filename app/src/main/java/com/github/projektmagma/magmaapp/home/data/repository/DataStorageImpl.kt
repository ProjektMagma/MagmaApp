package com.github.projektmagma.magmaapp.home.data.repository

import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.repository.DataStorage
import kotlinx.coroutines.flow.MutableStateFlow

class DataStorageImpl : DataStorage {

    private val _notebookStorage = mutableListOf<Notebook>()
    private val _selectedNotebook = MutableStateFlow<Notebook?>(null)

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

    // DO WYBIERANIA NOTEBOOKA POTRZEBNE ABY BYŁY MOŻLIWE MODYFIKACJE NOTE
    override fun selectNotebookById(id: String) {
        _selectedNotebook.value = getNotebookById(id)
    }

    override fun getSelectedNotebook(): Notebook = _selectedNotebook.value ?: Notebook()

    override fun addNote(note: Note) {
        _selectedNotebook.value?.notes?.add(note)
    }

    override fun addNotes(notes: List<Note>) {
        _selectedNotebook.value?.notes?.addAll(notes)
    }

    override fun getNotes(): List<Note> {
        return _selectedNotebook.value?.notes ?: emptyList()
    }

    override fun updateNote(note: Note) {
        val index = _selectedNotebook.value?.notes?.indexOfFirst { it.id == note.id } ?: return
        _selectedNotebook.value?.notes[index] = note
    }

    override fun getNoteById(id: String): Note {
        val index = _selectedNotebook.value?.notes?.indexOfFirst { it.id == id } ?: return Note()
        return _selectedNotebook.value?.notes[index] ?: Note()
    }

    override fun removeNote(note: Note) {
        _selectedNotebook.value?.notes?.remove(note)
    }
}