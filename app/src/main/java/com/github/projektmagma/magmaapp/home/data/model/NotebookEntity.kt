package com.github.projektmagma.magmaapp.home.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notebook")
data class NotebookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: String = "",
    val title: String = "",
)
