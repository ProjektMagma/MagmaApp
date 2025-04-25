package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.SelectNotebookIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.AddNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.GetNoteByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.GetNotesUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.RemoveNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.UpdateNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.GetNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.UpdateNotebookUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val updateNotebookUseCase: UpdateNotebookUseCase,
    private val getNotebookByIdUseCase: GetNotebookByIdUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val selectNotebookIdUseCase: SelectNotebookIdUseCase
) : ViewModel() {

    private val _notebook = MutableStateFlow(Notebook())
    val notebook = _notebook.asStateFlow()

    private val _notes = MutableStateFlow<SnapshotStateList<Note>>(mutableStateListOf())
    val notes = _notes.asStateFlow()

    fun selectNotebookId(id: String) {
        selectNotebookIdUseCase.execute(id)
        viewModelScope.launch {
            _notebook.value = getNotebookByIdUseCase.execute(id)
            _notes.value = getNotesUseCase.execute()
        }
    }

    fun updateNotebook(notebook: Notebook) {
        viewModelScope.launch {
            updateNotebookUseCase.execute(notebook)
        }
    }

    fun addNote(note: NoteDto) {
        viewModelScope.launch {
            when (val result = addNoteUseCase.execute(note)) {
                is Result.Success -> {
                    _notes.value.add(result.data)
                }

                is Result.Error -> {
                    // TODO przyjdzie jeszcze to handlowac
                }
            }
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            when (removeNoteUseCase.execute(note)) {
                is Result.Success -> {
                    _notes.value.remove(note)
                }

                is Result.Error -> {
                    // TODO przyjdzie jeszcze to handlowac
                }
            }
        }
    }

    fun getNoteById(id: String): Note {
        return getNoteByIdUseCase.execute(id)
    }


    fun updateNote(note: Note) {
        viewModelScope.launch {
            _notes.value.map { 
                if (it.id == note.id) note else it
            }
            updateNoteUseCase.execute(note)
        }
    }
}