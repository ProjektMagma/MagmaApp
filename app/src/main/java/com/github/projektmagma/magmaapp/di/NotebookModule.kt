package com.github.projektmagma.magmaapp.di

import com.github.projektmagma.magmaapp.home.data.repository.NotebookRepositoryImpl
import com.github.projektmagma.magmaapp.home.domain.repository.NotebookRepository
import com.github.projektmagma.magmaapp.home.domain.use_case.AddNotebookUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.GetNotebookByIdUseCase
import com.github.projektmagma.magmaapp.home.domain.use_case.GetNotebooksUseCase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val notebookModule = module {
    single<NotebookRepository> { NotebookRepositoryImpl(get()) }
    single { AddNotebookUseCase(get()) }
    single { GetNotebooksUseCase(get()) }
    single { GetNotebookByIdUseCase(get()) }

    single { Firebase.database("https://magmaapp-a5c52-default-rtdb.europe-west1.firebasedatabase.app").reference }
}