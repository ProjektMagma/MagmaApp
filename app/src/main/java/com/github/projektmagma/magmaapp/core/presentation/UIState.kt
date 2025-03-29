package com.github.projektmagma.magmaapp.core.presentation

sealed class UIState {
    object Loading : UIState()
    object Success : UIState()
    object Error : UIState()
}