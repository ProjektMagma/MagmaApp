package com.github.projektmagma.magmaapp.di

import android.content.Context
import androidx.room.Room
import com.github.projektmagma.magmaapp.auth.data.PasswordEncryption
import com.github.projektmagma.magmaapp.auth.domain.Encryption
import com.github.projektmagma.magmaapp.auth.domain.use_cases.EncryptUseCase
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.core.data.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Encryption> { PasswordEncryption() }
    single { EncryptUseCase(get()) }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "MagmaAppDatabase.db"
        )
            .createFromAsset("MagmaAppDatabase.db")
            .build()
    }

    viewModel { AuthViewModel(get(), get()) }
}