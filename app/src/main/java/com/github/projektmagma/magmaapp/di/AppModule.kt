package com.github.projektmagma.magmaapp.di

import android.app.Application
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.github.projektmagma.magmaapp.app.MainViewModel
import com.github.projektmagma.magmaapp.auth.data.repository.LoginPreferencesRepositoryImpl
import com.github.projektmagma.magmaapp.auth.data.repository.UserRepositoryImpl
import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository
import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.auth.domain.use_case.GetUserPreferencesUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.LoginUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.LogoutUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.RegisterUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.SaveUserPreferencesUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateEmail
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidatePassword
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateRepeatedPassword
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.core.domain.repository.NotebookRepository
import com.github.projektmagma.magmaapp.core.domain.use_case.AddNotebookUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetNotebookUseCase
import com.github.projektmagma.magmaapp.home.presentation.HomeViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Firebase.auth }

    single<UserRepository> { UserRepositoryImpl(get()) }

    single { ValidateEmail() }
    single { ValidatePassword() }

    single { RegisterUserUseCase(get()) }
    single { LoginUserUseCase(get()) }
    single { LogoutUseCase(get()) }

    single { ValidateEmail() }
    single { ValidatePassword() }
    single { ValidateRepeatedPassword() }

    single { NotebookRepository() }
    single { AddNotebookUseCase(get()) }
    single { GetNotebookUseCase(get()) }

    single {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Application>().applicationContext.filesDir.resolve("user_prefs.preferences_pb") }
        )
    }

    single<LoginPreferencesRepository> { LoginPreferencesRepositoryImpl(get()) }

    single { GetUserPreferencesUseCase(get()) }
    single { SaveUserPreferencesUseCase(get()) }

    viewModel { AuthViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel { MainViewModel(get(), get()) }
}