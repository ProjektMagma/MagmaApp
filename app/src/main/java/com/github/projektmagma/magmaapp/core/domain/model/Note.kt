package com.github.projektmagma.magmaapp.core.domain.model

import androidx.compose.runtime.MutableState

data class Note(
    val id: Int,
    val title: MutableState<String>,
    val content: MutableState<String>,
    val date: String
)