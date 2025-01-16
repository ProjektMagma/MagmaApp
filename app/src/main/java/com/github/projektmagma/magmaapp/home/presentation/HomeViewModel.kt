package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.core.domain.model.Note
import com.github.projektmagma.magmaapp.core.domain.model.Notebook
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val auth: FirebaseAuth,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user = _user.asStateFlow()

    init {
        getUser()
    }

    fun getUser(){
        viewModelScope.launch {
            _user.value = auth.currentUser
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute()
        }
    }
    
    val sampleData = mutableStateListOf(
        Notebook(
            1, "Matematyka", mutableListOf(
                Note(1, "Trygonometria", "Bla bla bla", ""),
                Note(2, "Wielomiany", "Bla bla bla", ""),
                Note(3, "Prawdopodobieństwo", "Bla bla bla", ""),
                Note(4, "Równania różniczkowe", "Bla bla bla", ""),
                Note(5, "Geometria analityczna", "Bla bla bla", ""),
                Note(6, "Algebra", "Bla bla bla", "")
            )
        ),
        Notebook(
            2, "Zakupy", mutableListOf(
                Note(1, "Biedronka", "Bla bla bla", ""),
                Note(2, "Lidl", "Bla bla bla", ""),
                Note(3, "Auchan", "Bla bla bla", "")
            )
        ),
        Notebook(
            3, "Informatyka", mutableListOf(
                Note(1, "Java", "Bla bla bla", ""),
                Note(2, "Python", "Bla bla bla", ""),
                Note(3, "Kotlin", "Bla bla bla", ""),
                Note(4, "C#", "Bla bla bla", ""),
                Note(5, "Gotowanie kernela z Ziel", "Bla bla bla", "")
            )
        ),
        Notebook(
            4, "Zdrowie", mutableListOf(
                Note(1, "Dieta", "Bla bla bla", ""),
                Note(2, "Ćwiczenia", "Bla bla bla", ""),
                Note(3, "Suplementy", "Bla bla bla", ""),
                Note(4, "Zdrowie psychiczne", "Bla bla bla", "")
            )
        )
    )
}