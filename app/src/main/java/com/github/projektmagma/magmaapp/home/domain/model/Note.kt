package com.github.projektmagma.magmaapp.home.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.github.projektmagma.magmaapp.home.data.model.NoteDto

data class Note(
    val id: String = "",
    val title: MutableState<String> = mutableStateOf(""),
    val content: MutableState<String> = mutableStateOf(""),
    val date: String = ""
)

fun NoteDto.toDomain(): Note {
    return Note(
        id = id,
        title = mutableStateOf(title),
        content = mutableStateOf(content),
        date = timestamp.toUiDate()
    )
}
