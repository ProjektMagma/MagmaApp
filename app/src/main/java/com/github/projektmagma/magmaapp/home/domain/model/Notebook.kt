package com.github.projektmagma.magmaapp.home.domain.model

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto

data class Notebook(
    val id: Int,
    val title: String,
    val notes: SnapshotStateList<Note>
)

fun NotebookDto.toDomain(): Notebook {
    return Notebook(
        id = id,
        title = title,
        notes = notes.map { it.toDomain() }.toMutableStateList()
    )
}