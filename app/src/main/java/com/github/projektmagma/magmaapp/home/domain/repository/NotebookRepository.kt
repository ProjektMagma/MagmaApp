package com.github.projektmagma.magmaapp.home.domain.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Notebook

interface NotebookRepository {
    suspend fun addNotebook(notebook: NotebookDto): Result<Notebook, Error>
    suspend fun getAllNotebooks(): SnapshotStateList<Notebook>
    suspend fun updateNotebook(notebook: Notebook): Result<Unit, Error>
    fun getNotebookById(id: String): Notebook
    suspend fun removeNotebook(notebook: Notebook): Result<Unit, Error>
}