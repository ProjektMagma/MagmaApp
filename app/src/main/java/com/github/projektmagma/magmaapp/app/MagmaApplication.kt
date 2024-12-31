package com.github.projektmagma.magmaapp.app

import android.app.Application
import com.github.projektmagma.magmaapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MagmaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MagmaApplication)
            modules(appModule)
        }
    }
}