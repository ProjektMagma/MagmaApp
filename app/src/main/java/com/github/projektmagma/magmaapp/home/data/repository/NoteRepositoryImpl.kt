package com.github.projektmagma.magmaapp.home.data.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.projektmagma.magmaapp.core.domain.use_case.SetUserNameUseCase
import com.github.projektmagma.magmaapp.core.util.Error
import com.github.projektmagma.magmaapp.core.util.Result
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import com.github.projektmagma.magmaapp.home.data.model.toDto
import com.github.projektmagma.magmaapp.home.data.safeFirebaseCall
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.domain.model.toDomain
import com.github.projektmagma.magmaapp.home.domain.repository.DataStorage
import com.github.projektmagma.magmaapp.home.domain.repository.NoteRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class NoteRepositoryImpl(
    private val database: DatabaseReference,
    private val dataStorage: DataStorage
) : NoteRepository {

    // TODO: DZIAŁA ALE MUSI BYĆ REFACTORING BO ŹLE TO WYGLĄDA

    override suspend fun addNote(note: NoteDto): Result<Note, Error> {
        return safeFirebaseCall {
            val noteDatabaseNode = database
                .child(dataStorage.getSelectedNotebook().id)
                .child("notes")
            val key = noteDatabaseNode.push().key ?: ""
            val noteWithId = note.copy(id = key)
            dataStorage.addNote(noteWithId.toDomain())
            noteDatabaseNode.child(key).setValue(noteWithId).await()

            noteWithId.toDomain()
        }
    }

    override suspend fun getAllNotes(): Result<SnapshotStateList<Note>, Error> {
        return safeFirebaseCall {
            val noteDatabaseNode = database
                .child(dataStorage.getSelectedNotebook().id)
                .child("notes")

            val snapshot = noteDatabaseNode.get().await()
            val notes = snapshot.children.mapNotNull {
                it.getValue(NoteDto::class.java)?.toDomain()
            }

            dataStorage.addNotes(notes)
            mutableStateListOf<Note>().apply {
                addAll(notes)
            }
        }


    }

    override suspend fun updateNote(note: Note): Result<Unit, Error> {
        return safeFirebaseCall {
            val noteDatabaseNode = database
                .child(dataStorage.getSelectedNotebook().id)
                .child("notes")

            noteDatabaseNode
                .child(note.id).setValue(note.toDto()).await()

            dataStorage.updateNote(note)
        }
    }

    override fun getNoteById(id: String): Note {
        return dataStorage.getNoteById(id)
    }

    override suspend fun removeNote(note: Note): Result<Unit, Error> {
        return safeFirebaseCall {
            val noteDatabaseNode = database
                .child(dataStorage.getSelectedNotebook().id)
                .child("notes")

            noteDatabaseNode
                .child(note.id)
                .removeValue().await()

            dataStorage.removeNote(note)
        }
    }
}