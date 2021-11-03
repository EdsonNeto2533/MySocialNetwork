package com.example.mysocialnetwork.utilsGeneric

import android.app.Application
import com.example.mysocialnetwork.utilsGeneric.di.domain
import com.example.mysocialnetwork.utilsGeneric.di.viewModels

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