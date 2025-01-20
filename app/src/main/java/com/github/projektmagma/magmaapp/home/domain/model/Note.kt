package com.github.projektmagma.magmaapp.home.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Note(
    val id: String = "",
    val title: MutableState<String>,
    val content: MutableState<String>,
    val date: String
)

fun NoteDto.toDomain(): Note {
    return Note(
        id = id,
        title = mutableStateOf(title),
        content = mutableStateOf(content),
        date = timestamp.toUiDate()
    )
}

fun Long.toUiDate(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    val date = Date(this)

    return formatter.format(date)
}
