package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAppThemeUseCase
import com.github.projektmagma.magmaapp.core.presentation.UIState
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.use_case.note.AddNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.GetNoteByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.GetNotesUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.RemoveNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.UpdateNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.UpdateModDateNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.GetNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.RemoveNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.SelectNotebookIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.UpdateNotebookUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class NotesViewModel(
    private val updateNotebookUseCase: UpdateNotebookUseCase,
    private val getNotebookByIdUseCase: GetNotebookByIdUseCase,
    private val removeNotebookUseCase: RemoveNotebookUseCase,
    private val updateModDateNotebookUseCase: UpdateModDateNotebookUseCase,
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

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()


    fun selectNotebookId(id: String) {
        _uiState.value = UIState.Loading
        selectNotebookIdUseCase.execute(id)
        viewModelScope.launch {
            _notebook.value = getNotebookByIdUseCase.execute(id)
            when (val result = withTimeoutOrNull(5000) { getNotesUseCase.execute() }) {
                is Result.Success -> {
                    _notes.value = result.data
                    _uiState.value = UIState.Success
                }

                is Result.Error -> {
                    _errorFlow.emit(result.error.messageId)
                    _uiState.value = UIState.Error
                    Log.d("NotesViewModel", "Error: ${result.error.messageId}")
                }

                null -> {
                    //TODO: Tymczasowe obejście systemu. Idk ale tutaj safeFirebaseCall nie działa (patrz getNotesUseCase)
                    _uiState.value = UIState.Error

                }
            }
        }
    }

    fun updateNotebook() {
        viewModelScope.launch {
            _notebook.value.notes.let {
                it.clear()
                it.addAll(_notes.value)
            }
            val result = updateNotebookUseCase.execute(_notebook.value)
            if (result is Result.Error) {
                _errorFlow.emit(result.error.messageId)
            }
        }
    }

    fun removeNotebook() {
        viewModelScope.launch {
            removeNotebookUseCase.execute(_notebook.value)
        }
    }

    fun changeModDateNotebook() {
        viewModelScope.launch {
            updateModDateNotebookUseCase.execute(_notebook.value)
        }
    }

    fun addNote(title: String) {
        viewModelScope.launch {
            when (val result = addNoteUseCase.execute(
                NoteDto(
                title = title,
                createdAt = System.currentTimeMillis(),
            ))) {
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