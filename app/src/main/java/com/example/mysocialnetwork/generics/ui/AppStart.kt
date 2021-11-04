package com.example.mysocialnetwork.generics.ui

import android.app.Application
import com.example.mysocialnetwork.generics.di.domain
import com.example.mysocialnetwork.generics.di.viewModels

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppStart: Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@AppStart)
            modules(domain, viewModels)
        }
        super.onCreate()
    }
}