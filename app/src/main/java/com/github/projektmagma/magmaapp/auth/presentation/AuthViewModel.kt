package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_cases.EncryptUseCase
import com.github.projektmagma.magmaapp.core.domain.repositories.GroupRepository
import com.github.projektmagma.magmaapp.core.domain.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
    private val encryptionUseCase: EncryptUseCase,
    userRepository: UserRepository,
    groupRepository: GroupRepository
): ViewModel() {

    val password = mutableStateOf("")

    private val _encryptedPassword = MutableStateFlow("")
    val encryptedPassword= _encryptedPassword.asStateFlow()

    val userRelation = userRepository.getUsers()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )

    val groupRelation = groupRepository.getGroups()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )

    fun encryptPassword(password: String) {
        viewModelScope.launch {
            _encryptedPassword.value = encryptionUseCase.execute(password)
        }
    }
}