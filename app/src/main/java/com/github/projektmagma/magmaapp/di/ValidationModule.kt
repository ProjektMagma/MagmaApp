package com.github.projektmagma.magmaapp.di

import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateEmail
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidatePassword
import com.github.projektmagma.magmaapp.auth.domain.use_case.ValidateRepeatedPassword
import org.koin.dsl.module

val validationModule = module {
    single { ValidateEmail() }
    single { ValidatePassword() }
    single { ValidateRepeatedPassword() }
}