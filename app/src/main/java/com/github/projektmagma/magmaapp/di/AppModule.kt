package com.github.projektmagma.magmaapp.di

import com.github.projektmagma.magmaapp.auth.data.repository.UserRepositoryImpl
import com.github.projektmagma.magmaapp.auth.domain.repository.UserRepository
import com.github.projektmagma.magmaapp.auth.domain.use_case.LoginUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.RegisterUserUseCase
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateEmail
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidatePassword
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateRepeatedPassword
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.auth.presentation.common.AuthModifiers
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
    single { ValidateEmail() }
    single { ValidatePassword() }
    single { ValidateRepeatedPassword() }

    single { AuthModifiers() }

    viewModel { AuthViewModel(get(), get(), get(), get()) }
}