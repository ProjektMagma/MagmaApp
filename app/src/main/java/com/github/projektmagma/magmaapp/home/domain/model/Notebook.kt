package com.github.projektmagma.magmaapp.home.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto

data class Notebook(
    val id: String = "",
    var title: MutableState<String> = mutableStateOf(""),
    val notes: SnapshotStateList<Note> = mutableStateListOf()
)

fun NotebookDto.toDomain(): Notebook {
    val tmpList = mutableStateListOf<Note>()
    notes.forEach { note ->
        tmpList.add(note.value.toDomain())
    }

    return Notebook(
        id = id,
        title = mutableStateOf(title),
        notes = tmpList
    )
}