package com.github.projektmagma.magmaapp.di

import androidx.room.Room
import com.github.projektmagma.magmaapp.auth.data.PasswordEncryption
import com.github.projektmagma.magmaapp.auth.domain.Encryption
import com.github.projektmagma.magmaapp.auth.domain.use_cases.EncryptUseCase
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.core.data.database.AppDatabase
import com.github.projektmagma.magmaapp.core.data.repository.GroupRepositoryImpl
import com.github.projektmagma.magmaapp.core.data.repository.UserRepositoryImpl
import com.github.projektmagma.magmaapp.core.domain.repositories.GroupRepository
import com.github.projektmagma.magmaapp.core.domain.repositories.UserRepository
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
            .fallbackToDestructiveMigration()
            .createFromAsset("MagmaAppDatabase.db")
            .build()
    }

    single<UserRepository> { UserRepositoryImpl(get<AppDatabase>().userDao()) }
    single<GroupRepository> { GroupRepositoryImpl(get<AppDatabase>().groupDao()) }

    viewModel { AuthViewModel(get(), get(), get()) }
}