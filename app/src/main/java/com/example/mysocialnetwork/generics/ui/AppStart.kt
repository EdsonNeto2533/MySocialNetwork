package com.example.mysocialnetwork.generics.ui

import android.app.Application
import com.example.mysocialnetwork.featureAuth.utils.di.diAuth
import com.example.mysocialnetwork.featureAuth.utils.di.viewModelsAuth
import com.example.mysocialnetwork.featureDashboard.di.diDashboard
import com.example.mysocialnetwork.featureDashboard.di.viewModelDashboard
import com.example.mysocialnetwork.featureUserProfile.di.diProfile
import com.example.mysocialnetwork.featureUserProfile.di.viewModelProfile
import com.example.mysocialnetwork.generics.di.domain

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppStart : Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@AppStart)
            modules(domain, viewModelProfile, diDashboard, diAuth, viewModelsAuth, viewModelDashboard, diProfile)
        }
        super.onCreate()
    }
}