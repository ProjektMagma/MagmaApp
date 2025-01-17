package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.domain.model.Notebook
import com.github.projektmagma.magmaapp.core.domain.use_case.AddNotebookUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetNotebookUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val auth: FirebaseAuth,
    private val logoutUseCase: LogoutUseCase,
    private val getNotebookUseCase: GetNotebookUseCase,
    private val addNotebookUseCase: AddNotebookUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user = _user.asStateFlow()
    var notebooks = getAllNotebooks()
        private set

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            _user.value = auth.currentUser
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute()
        }
    }

    fun addNotebook(notebook: Notebook) {
        addNotebookUseCase.execute(notebook)
        notebooks = getAllNotebooks()
    }

    fun getNotebook(index: Int) = getNotebookUseCase.execute(index)

    fun getAllNotebooks() = getNotebookUseCase.execute()

}