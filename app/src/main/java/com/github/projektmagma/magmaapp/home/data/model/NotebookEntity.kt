package com.github.projektmagma.magmaapp.home.data.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.projektmagma.magmaapp.home.domain.model.Notebook

@Entity(tableName = "notebook")
data class NotebookEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val title: String? = "",
)

fun NotebookEntity.toDomain(): Notebook {
    return Notebook(
        id = id,
        title = if (title.isNullOrEmpty()) mutableStateOf("") else mutableStateOf(title),
    )
}
