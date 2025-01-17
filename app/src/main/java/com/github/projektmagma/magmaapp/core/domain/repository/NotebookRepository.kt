package com.github.projektmagma.magmaapp.core.domain.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.domain.model.Notebook

class NotebookRepository {

    private val notebooks = mutableStateListOf<Notebook>()

    fun getAllNotebooks(): SnapshotStateList<Notebook> {
        return notebooks
    }

    fun getNotebook(index: Int): Notebook {
        return notebooks[index]
    }

    fun addNotebook(notebook: Notebook) {
        notebooks.add(notebook)
    }
}