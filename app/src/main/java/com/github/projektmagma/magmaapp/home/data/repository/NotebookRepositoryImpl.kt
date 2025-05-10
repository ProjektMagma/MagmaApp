package com.github.projektmagma.magmaapp.home.data.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.data.model.toDto
import com.github.projektmagma.magmaapp.home.data.safeFirebaseCall
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.model.toDomain
import com.github.projektmagma.magmaapp.home.domain.repository.DataStorage
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class NotebookRepositoryImpl(
    private val database: DatabaseReference,
    private val dataStorage: DataStorage
) : NotebookRepository {
    override suspend fun addNotebook(notebook: NotebookDto): Result<Notebook, Error> {
        return safeFirebaseCall {
            val key = database.push().key ?: ""
            val notebookWithId = notebook.copy(id = key)
            dataStorage.addNotebook(notebookWithId.toDomain())
            database.child(key).setValue(notebookWithId).await()

            notebookWithId.toDomain()
        }
    }

    override suspend fun getAllNotebooks(): Result<SnapshotStateList<Notebook>, Error> {
        return safeFirebaseCall {
            val snapshot = database.get().await()
            val list = snapshot.children.mapNotNull {
                it.getValue(NotebookDto::class.java)?.toDomain()
            }
            dataStorage.addNotebooks(list)

            mutableStateListOf<Notebook>().apply {
                addAll(list)
            }
        }
    }


    override suspend fun updateNotebook(
        notebook: Notebook,
    ): Result<Unit, Error> {
        return safeFirebaseCall {
            notebook.lastModified.longValue = System.currentTimeMillis()
            database.child(notebook.id).setValue(notebook.toDto()).await()
            dataStorage.updateNotebook(notebook)
        }
    }

    override fun getNotebookById(id: String): Notebook {
        return dataStorage.getNotebookById(id)
    }

    override suspend fun removeNotebook(notebook: Notebook): Result<Unit, Error> {
        return safeFirebaseCall {
            database.child(notebook.id).removeValue().await()
            dataStorage.removeNotebook(notebook)
        }
    }

    override suspend fun updateModDateNotebook(notebook: Notebook): Result<Unit, Error> {
        return safeFirebaseCall {
            notebook.lastModified.longValue = System.currentTimeMillis()
            database.child(notebook.id).child("lastModified")
                .setValue(notebook.toDto().lastModified).await()
            dataStorage.updateNotebook(notebook)
        }
    }

    // DO WYBIERANIA NOTEBOOKA POTRZEBNE ABY BYŁY MOŻLIWE MODYFIKACJE NOTE
    override fun selectNotebookById(id: String) = dataStorage.selectNotebookId(id)

}