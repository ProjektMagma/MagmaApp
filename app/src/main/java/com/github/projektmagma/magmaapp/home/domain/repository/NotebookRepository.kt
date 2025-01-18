package com.github.projektmagma.magmaapp.home.domain.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Notebook

interface NotebookRepository {
    suspend fun addNotebook(notebook: NotebookDto, userId: String): Result<Unit, Error>
    fun getAllNotebooks(userId: String): SnapshotStateList<Notebook>
    fun getNotebook(index: Int): Notebook
}