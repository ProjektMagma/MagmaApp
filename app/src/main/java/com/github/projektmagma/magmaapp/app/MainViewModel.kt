package com.github.projektmagma.magmaapp.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.projektmagma.magmaapp.auth.domain.use_case.GetUserPreferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
): ViewModel() {
    suspend fun getUserId(): Boolean{
        return getUserPreferencesUseCase.execute().last()
    }
}