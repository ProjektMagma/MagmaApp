package com.github.projektmagma.magmaapp.di

import com.github.projektmagma.magmaapp.app.MainViewModel
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.home.presentation.home_screen.HomeViewModel
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.EditNoteViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewmodelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::EditNoteViewModel)
}