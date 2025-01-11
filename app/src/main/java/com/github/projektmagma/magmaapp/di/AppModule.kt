package com.github.projektmagma.magmaapp.di

import com.github.projektmagma.magmaapp.auth.data.LoginValidation
import com.github.projektmagma.magmaapp.auth.domain.Validation
import com.github.projektmagma.magmaapp.auth.domain.use_cases.LoginValidationUseCase
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Validation> { LoginValidation() }
    single { LoginValidationUseCase(get()) }

    viewModel { AuthViewModel(get()) }
}