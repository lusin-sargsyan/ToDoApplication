package com.example.todoapplication.presentation

import android.app.Application
import com.example.todoapplication.di.repositoryModule
import com.example.todoapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationModule : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(repositoryModule, viewModelModule)
        }
    }
}