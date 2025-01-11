package com.github.projektmagma.magmaapp.di

import com.github.projektmagma.magmaapp.auth.data.repository.UserRepositoryImpl
import com.github.projektmagma.magmaapp.auth.domain.EmailValidation
import com.github.projektmagma.magmaapp.auth.domain.PasswordValidation
import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.auth.domain.use_cases.LoginUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_cases.RegisterUserUseCase
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Firebase.auth }

    single<UserRepository> { UserRepositoryImpl(get()) }

    single { EmailValidation() }
    single { PasswordValidation() }

    single { RegisterUserUseCase(get(), get(), get())}
    single { LoginUserUseCase(get(), get()) }

    viewModel { AuthViewModel(get(), get()) }
}