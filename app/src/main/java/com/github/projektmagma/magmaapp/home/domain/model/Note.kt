package com.github.projektmagma.magmaapp.home.domain.model

import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val date: String
)

fun NoteDto.toDomain(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        date = timestamp.toUiDate()
    )
}

fun Long.toUiDate(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    val date = Date(this)

    return formatter.format(date)
}

