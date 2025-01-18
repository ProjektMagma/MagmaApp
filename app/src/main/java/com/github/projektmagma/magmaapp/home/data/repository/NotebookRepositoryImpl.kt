package com.github.projektmagma.magmaapp.home.data.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.data.DataError
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.model.toDomain
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

class NotebookRepositoryImpl(
    private val database: DatabaseReference,
) : NotebookRepository {
    private val notebooks = mutableStateListOf<Notebook>()

    override suspend fun addNotebook(notebook: NotebookDto, userId: String): Result<Unit, Error> {
        return try {
            database.child("notebooks").child(userId).push().setValue(notebook).await()
            notebooks.add(notebook.toDomain())
            Result.Success(Unit)
        } catch (e: FirebaseNetworkException) {
            Result.Error(DataError.NetworkError.NETWORK_ERROR)
        } catch (e: FirebaseTooManyRequestsException) {
            Result.Error(DataError.NetworkError.TOO_MANY_REQUESTS)
        } catch (e: FirebaseException) {
            Result.Error(DataError.NetworkError.SERVER_ERROR)
        } catch (e: Exception) {
            Result.Error(DataError.NetworkError.UNKNOWN_ERROR)
        }
    }

    override fun getAllNotebooks(userId: String): SnapshotStateList<Notebook> {
//        notebooks.clear() idk
        database.child("notebooks").child(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (notebookSnapshot in snapshot.children) {
                        val notebook = notebookSnapshot.getValue(NotebookDto::class.java)
                        if (notebook != null) {
                            notebooks.add(notebook.toDomain())
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        return notebooks
    }

    override fun getNotebookById(index: Int): Notebook {
        return notebooks[index]
    }
}