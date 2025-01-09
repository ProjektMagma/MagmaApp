package com.github.projektmagma.magmaapp.di

import com.github.projektmagma.magmaapp.auth.data.PasswordEncryption
import com.github.projektmagma.magmaapp.auth.domain.Encryption
import com.github.projektmagma.magmaapp.auth.domain.use_cases.EncryptUseCase
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Encryption> { PasswordEncryption() }
    single { EncryptUseCase(get()) }

    viewModel { AuthViewModel(get()) }
}