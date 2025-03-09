package com.github.projektmagma.magmaapp.home.presentation.home_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetCurrentUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.use_case.AddNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.GetNotebooksUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.RemoveNotebookUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getNotebooksUseCase: GetNotebooksUseCase,
    private val addNotebookUseCase: AddNotebookUseCase,
    private val removeNotebookUseCase: RemoveNotebookUseCase,
    private val setAutoLogInUserUseCase: SetAutoLogInUserUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user = _user.asStateFlow()

    private val _notebooks = MutableStateFlow<SnapshotStateList<Notebook>>(mutableStateListOf())
    val notebooks = _notebooks.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute()
            setAutoLogInUserUseCase.execute(false)
        }
    }

    init {
        viewModelScope.launch {
            _user.value = getCurrentUserUseCase.execute()
            _notebooks.value = getNotebooksUseCase.execute()
        }
    }

    fun addNotebook(notebook: NotebookDto) {
        viewModelScope.launch {
            when (val result = addNotebookUseCase.execute(notebook)) {
                is Result.Success -> {
                    _notebooks.value.add(result.data)
                }

                is Result.Error -> {
                    // TODO przyjdzie jeszcze to handlowac
                }
            }
        }
    }

    fun removeNotebook(notebook: Notebook) {
        viewModelScope.launch {
            when (removeNotebookUseCase.execute(notebook)) {
                is Result.Success -> {
                    _notebooks.value.remove(notebook)
                }

                is Result.Error -> {
                    // TODO przyjdzie jeszcze to handlowac
                }
            }
        }
    }
}