package com.github.projektmagma.magmaapp.auth.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_cases.EncryptUseCase
import com.github.projektmagma.magmaapp.core.data.AppDatabase
import com.github.projektmagma.magmaapp.core.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val encryptionUseCase: EncryptUseCase,
    private val database: AppDatabase
): ViewModel() {

    val password = mutableStateOf("")

    private val _encryptedPassword = MutableStateFlow("")
    val encryptedPassword= _encryptedPassword.asStateFlow()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    fun encryptPassword(password: String) {
        viewModelScope.launch {
            _encryptedPassword.value = encryptionUseCase.execute(password)
        }
    }

    fun getUsers(){
       viewModelScope.launch {
           withContext(Dispatchers.IO) {
               _users.value = database.userDao().getUsers()
           }
       }
    }
}