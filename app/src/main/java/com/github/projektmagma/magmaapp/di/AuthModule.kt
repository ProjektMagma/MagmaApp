package com.github.projektmagma.magmaapp.di

import android.app.Application
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.github.projektmagma.magmaapp.auth.data.repository.LoginPreferencesRepositoryImpl
import com.github.projektmagma.magmaapp.auth.data.repository.UserRepositoryImpl
import com.github.projektmagma.magmaapp.auth.domain.repository.LoginPreferencesRepository
import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.core.domain.use_case.GetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.LoginUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.RegisterUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.SetAutoLogInUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.GetCurrentUserUseCase
import com.github.projektmagma.magmaapp.core.domain.use_case.LogoutUseCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val authModule = module {
    single { Firebase.auth }

    single<UserRepository> { UserRepositoryImpl(get()) }

    single { RegisterUserUseCase(get()) }
    single { LoginUserUseCase(get()) }
    single { LogoutUseCase(get()) }
    single { GetCurrentUserUseCase(get()) }

    single<LoginPreferencesRepository> { LoginPreferencesRepositoryImpl(get()) }

    single { GetAutoLogInUserUseCase(get()) }
    single { SetAutoLogInUserUseCase(get()) }

    single {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Application>().applicationContext.filesDir.resolve("user_prefs.preferences_pb") }
        )
    }
}