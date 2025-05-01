package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.use_case.note.AddNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.GetNoteByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.GetNotesUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.RemoveNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.UpdateNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.GetNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.RemoveNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.SelectNotebookIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.UpdateNotebookUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val updateNotebookUseCase: UpdateNotebookUseCase,
    private val getNotebookByIdUseCase: GetNotebookByIdUseCase,
    private val removeNotebookUseCase: RemoveNotebookUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val selectNotebookIdUseCase: SelectNotebookIdUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase
) : ViewModel() {


    private val _notebook = MutableStateFlow(Notebook())
    val notebook = _notebook.asStateFlow()

    private val _notes = MutableStateFlow<SnapshotStateList<Note>>(mutableStateListOf())
    val notes = _notes.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Int>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _appTheme = MutableStateFlow(false)
    val appTheme = _appTheme.asStateFlow()

    fun selectNotebookId(id: String) {
        selectNotebookIdUseCase.execute(id)
        viewModelScope.launch {
            _notebook.value = getNotebookByIdUseCase.execute(id)
            when (val result = getNotesUseCase.execute()) {
                is Result.Success -> {
                    _notes.value = result.data
                }

                is Result.Error -> {
                    _errorFlow.emit(result.error.messageId)
                }
            }
        }
    }

    fun updateNotebook(notebook: Notebook) {
        viewModelScope.launch {
            val result = updateNotebookUseCase.execute(notebook)
            if (result is Result.Error) {
                _errorFlow.emit(result.error.messageId)
            }
        }
    }

    fun removeNotebook(notebook: Notebook) {
        viewModelScope.launch {
            removeNotebookUseCase.execute(notebook)
        }
    }

    fun addNote(note: NoteDto) {
        viewModelScope.launch {
            when (val result = addNoteUseCase.execute(note)) {
                is Result.Success -> {
                    _notes.value.add(result.data)
                }

                is Result.Error -> {
                    _errorFlow.emit(result.error.messageId)
                }
            }
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            when (val result = removeNoteUseCase.execute(note)) {
                is Result.Success -> {
                    _notes.value.remove(note)
                }

                is Result.Error -> {
                    _errorFlow.emit(result.error.messageId)
                }
            }
        }
    }

    fun getNoteById(id: String): Note {
        return getNoteByIdUseCase.execute(id)
    }


    fun updateNote(note: Note) {
        viewModelScope.launch {
            when (val result = updateNoteUseCase.execute(note)) {
                is Result.Success -> {
                    _notes.value.map { if (it.id == note.id) note else it }

                }

                is Result.Error -> {
                    _errorFlow.emit(result.error.messageId)
                }
            }
        }
    }

    fun getAppTheme() {
        viewModelScope.launch {
            _appTheme.value = getAppThemeUseCase.execute()
        }
    }
}