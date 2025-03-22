package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.use_case.GetNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.UpdateNotebookUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotebookByIdUseCase: GetNotebookByIdUseCase,
    private val updateNotebookUseCase: UpdateNotebookUseCase
) : ViewModel() {

    private val _notebook = MutableStateFlow(Notebook())
    val notebook = _notebook.asStateFlow()

    private val _notes = MutableStateFlow<SnapshotStateList<Note>>(mutableStateListOf())
    val notes = _notes.asStateFlow()

    private val _note = MutableStateFlow(Note())
    val note = _note.asStateFlow()

    init {
        _notes.value.addAll(_notebook.value.notes)
    }

    fun setCurrentNote(note: Note) {
        _note.value = note
    }

    fun addNote(note: Note) {
        _notes.value.add(note)
    }

    fun removeNote(note: Note) {
        _notes.value.remove(note)
    }

    fun getNotebookById(notebookId: String) {
        viewModelScope.launch {
            _notebook.value = getNotebookByIdUseCase.execute(notebookId)
        }
    }

    fun updateNotebook(notebook: Notebook) {
        viewModelScope.launch {
            updateNotebookUseCase.execute(notebook)
        }
    }
}