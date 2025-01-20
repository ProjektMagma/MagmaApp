package com.github.projektmagma.magmaapp.home.data.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.data.safeFirebaseCall
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.model.toDomain
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NotebookRepositoryImpl(
    private val database: DatabaseReference,
) : NotebookRepository {
    private val notebooks = mutableStateListOf<Notebook>()

    override suspend fun addNotebook(notebook: NotebookDto): Result<Unit, Error> {
        return safeFirebaseCall {
            val key = database.push().key ?: ""
            database.child(key).setValue(notebook.copy(id = key)).await()
            notebooks.add(notebook.toDomain())
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
                            notebooks.addAll(tempNotebooks)
                            continuation.resume(tempNotebooks)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    override suspend fun updateNotebook(
        notebook: NotebookDto,
    ): Result<Unit, Error> {
        return safeFirebaseCall {
            database.child(notebook.id).setValue(notebook).await()
        }
    }

    override fun getNotebookById(index: Int): Notebook {
        return notebooks[index]
    }
}