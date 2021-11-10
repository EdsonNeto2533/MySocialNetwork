package com.example.mysocialnetwork.generics.di

import com.example.mysocialnetwork.featureDashboard.domain.repository.DashboardRepository
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardViewModel
import com.example.mysocialnetwork.featureAuth.featureLogin.domain.repository.LoginRepository
import com.example.mysocialnetwork.featureAuth.featureLogin.ui.LoginViewModel
import com.example.mysocialnetwork.featureAuth.featureRegister.ui.RegisterViewModel
import com.example.mysocialnetwork.generics.domain.HomeRepository
import com.example.mysocialnetwork.generics.ui.HomeViewModel
import com.example.mysocialnetwork.generics.utils.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domain = module {
    single {
        FirebaseAuth.getInstance()
    }
    single { FirebaseFirestore.getInstance() }

    factory {
        HomeRepository(mFirebaseAuth = get())
    }

    single {
        SharedPreferences(androidContext())
    }

}

val homeViewModel = module {
    viewModel {
        HomeViewModel(mHomeRepository = get())
    }
}


