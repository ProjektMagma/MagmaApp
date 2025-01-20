package com.github.projektmagma.magmaapp.home.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto

data class Notebook(
    val id: String = "",
    var title: MutableState<String> = mutableStateOf(""),
    val notes: SnapshotStateList<Note> = mutableStateListOf()
)

fun NotebookDto.toDomain(): Notebook {
    return Notebook(
        id = id,
        title = mutableStateOf(title),
        notes = notes.map { it.toDomain() }.toMutableStateList()
    )
}