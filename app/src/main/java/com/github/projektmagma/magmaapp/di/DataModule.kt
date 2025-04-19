package com.github.projektmagma.magmaapp.di

import com.github.projektmagma.magmaapp.home.data.repository.DataStorageImpl
import com.github.projektmagma.magmaapp.home.data.repository.NoteRepositoryImpl
import com.github.projektmagma.magmaapp.home.data.repository.NotebookRepositoryImpl
import com.github.projektmagma.magmaapp.home.domain.repository.DataStorage
import com.github.projektmagma.magmaapp.home.domain.repository.NoteRepository
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository
import com.github.projektmagma.magmaapp.home.domain.use_case.note.AddNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.GetNoteByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.GetNotesUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.RemoveNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.note.UpdateNoteUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.AddNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.GetNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.GetNotebooksUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.RemoveNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.SelectNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.notebook.UpdateNotebookUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val dataModule = module {

    single<DataStorage> { DataStorageImpl() }
    single<NotebookRepository> { NotebookRepositoryImpl(get(), get()) }
    single<NoteRepository> { NoteRepositoryImpl(get(), get()) }

    // Notebook
    single { AddNotebookUseCase(get()) }
    single { GetNotebooksUseCase(get()) }
    single { GetNotebookByIdUseCase(get()) }
    single { UpdateNotebookUseCase(get()) }
    single { RemoveNotebookUseCase(get()) }
    single { SelectNotebookByIdUseCase(get()) }

    // Note
    single { AddNoteUseCase(get()) }
    single { GetNotesUseCase(get()) }
    single { GetNoteByIdUseCase(get()) }
    single { UpdateNoteUseCase(get()) }
    single { RemoveNoteUseCase(get()) }

    factory {
        Firebase.database("https://magmaapp-a5c52-default-rtdb.europe-west1.firebasedatabase.app").reference
            .child("notebooks")
            .child(get<FirebaseAuth>().currentUser?.uid ?: "")
    }
}