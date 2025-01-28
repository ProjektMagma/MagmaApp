package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.use_case.GetNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.UpdateNotebookUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val getNotebookByIdUseCase: GetNotebookByIdUseCase,
    private val updateNotebookUseCase: UpdateNotebookUseCase
): ViewModel() {

    private val _notebook = MutableStateFlow(Notebook())
    val notebook = _notebook.asStateFlow()

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