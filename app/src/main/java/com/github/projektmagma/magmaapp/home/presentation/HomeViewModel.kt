package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetCurrentUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
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


    init {
        viewModelScope.launch {
            _user.value = getCurrentUserUseCase.execute()
            if (_user.value == null) logout()
            else _notebooks.value = getAllNotebooks()
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
//        _notebooks.value = getAllNotebooks()
    }

    fun getNotebook(index: Int) = getNotebookByIdUseCase.execute(index)

    fun setCurrentNotebookIndex(index: Int) {
        getNotebookByIdUseCase.lastNotebookIndex = index
    }

    fun getCurrentNotebookIndex() = getNotebookByIdUseCase.execute()

    fun getAllNotebooks() = getNotebooksUseCase.execute(_user.value!!.uid)
}