package com.github.projektmagma.magmaapp.app

import android.app.Application
import com.github.projektmagma.magmaapp.di.appModule
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MagmaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            Firebase.database.setPersistenceEnabled(true)
            Firebase.database
                .getReference()
                .child("notebooks")
                .keepSynced(true)
            androidContext(this@MagmaApplication)
            modules(appModule)
        }
    }
}