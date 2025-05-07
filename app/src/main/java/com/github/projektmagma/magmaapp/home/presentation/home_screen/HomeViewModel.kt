package com.github.projektmagma.magmaapp.home.presentation.home_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.core.domain.use_case.GetCurrentUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetUserNameUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.presentation.UIState
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.AddNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.GetNotebooksUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getNotebooksUseCase: GetNotebooksUseCase,
    private val addNotebookUseCase: AddNotebookUseCase,
    private val setAutoLogInUserUseCase: SetAutoLogInUserUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user = _user.asStateFlow()

    private val _notebooks = MutableStateFlow<SnapshotStateList<Notebook>>(mutableStateListOf())
    val notebooks = _notebooks.asStateFlow()

    private val _displayName = MutableStateFlow<String>("")
    val displayName = _displayName.asStateFlow()

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Int>()
    val errorFlow = _errorFlow.asSharedFlow()


    fun tryFetchAllData() {
        _uiState.value = UIState.Loading
        getFirebaseUser()
        refreshDisplayName()
        refreshNotebookList()

    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute()
            setAutoLogInUserUseCase.execute(false)
        }
    }

    fun getFirebaseUser() {
        viewModelScope.launch {
            _user.value = getCurrentUserUseCase.execute()
        }
    }

    fun refreshNotebookList() {
        viewModelScope.launch {
            when (val result = getNotebooksUseCase.execute()) {
                is Result.Success -> {
                    _notebooks.value = result.data
                    _uiState.value = UIState.Success
                }

                is Result.Error -> {
                    _uiState.value = UIState.Error
                }
            }
        }
    }

    fun refreshDisplayName() {
        _displayName.value = getUserNameUseCase.execute()
    }

    fun addNotebook(notebook: NotebookDto) {
        viewModelScope.launch {
            when (val result = addNotebookUseCase.execute(notebook)) {
                is Result.Success -> {
                    _notebooks.value.add(result.data)
                }

                is Result.Error -> {
                    _uiState.value = UIState.Error
                }
            }
        }
    }
}