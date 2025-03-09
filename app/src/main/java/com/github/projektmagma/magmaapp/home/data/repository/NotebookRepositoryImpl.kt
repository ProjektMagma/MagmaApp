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
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookStorage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NotebookRepositoryImpl(
    private val database: DatabaseReference,
    private val notebookStorage: NotebookStorage
) : NotebookRepository {
    override suspend fun addNotebook(notebook: NotebookDto): Result<Notebook, Error> {
        return safeFirebaseCall {
            val key = database.push().key ?: ""
            val notebookWithId = notebook.copy(id = key)
            notebookStorage.addNotebook(notebookWithId.toDomain())
            database.child(key).setValue(notebookWithId).await()

            notebookWithId.toDomain()
        }
    }

    override suspend fun getAllNotebooks(): SnapshotStateList<Notebook> {
        return suspendCoroutine { continuation ->
            val tempNotebooks = mutableStateListOf<Notebook>()
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (notebookSnapshot in snapshot.children) {
                        val notebook = notebookSnapshot.getValue(NotebookDto::class.java)
                        if (notebook != null) {
                            tempNotebooks.add(notebook.toDomain())
                        }
                        if (tempNotebooks.size == snapshot.children.count()) {
                            notebookStorage.addNotebooks(tempNotebooks)
                            continuation.resume(tempNotebooks)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resume(mutableStateListOf())
                }
            })
        }
    }


    override suspend fun updateNotebook(
        notebook: Notebook,
    ): Result<Unit, Error> {
        return safeFirebaseCall {
            database.child(notebook.id).setValue(notebook.toDto()).await()
            notebookStorage.updateNotebook(notebook)
        }
    }

    override fun getNotebookById(id: String): Notebook {
        return notebookStorage.getNotebookById(id)
    }

    override suspend fun removeNotebook(notebook: Notebook): Result<Unit, Error> {
        return safeFirebaseCall {
            database.child(notebook.id).removeValue().await()
            notebookStorage.removeNotebook(notebook)
        }
    }
}