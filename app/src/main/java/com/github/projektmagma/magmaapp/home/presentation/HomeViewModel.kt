package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetCurrentUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.model.toDomain
import com.github.projektmagma.magmaapp.home.domain.use_case.AddNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.GetNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.GetNotebooksUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getNotebooksUseCase: GetNotebooksUseCase,
    private val getNotebookByIdUseCase: GetNotebookByIdUseCase,
    private val addNotebookUseCase: AddNotebookUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user = _user.asStateFlow()

    private val _notebooks = MutableStateFlow<SnapshotStateList<Notebook>>(mutableStateListOf())
    val notebooks = _notebooks.asStateFlow()

    private val _notebook = MutableStateFlow(Notebook())
    val notebook = _notebook.asStateFlow()

    init {
        viewModelScope.launch {
            _user.value = getCurrentUserUseCase.execute()
            getAllNotebooks()
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute()
        }
    }

    fun addNotebook(notebook: NotebookDto) {
        viewModelScope.launch {
            addNotebookUseCase.execute(notebook, _user.value!!.uid)
        }
        _notebooks.value.add(notebook.toDomain())
    }

    fun getNotebook(index: Int){
        viewModelScope.launch {
            _notebook.value = getNotebookByIdUseCase.execute(index)
        }
    }

    private fun getAllNotebooks(){
        viewModelScope.launch {
            _notebooks.value = getNotebooksUseCase.execute(_user.value!!.uid)
        }
    }
}