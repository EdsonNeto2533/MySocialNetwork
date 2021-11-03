package com.example.mysocialnetwork.utilsGeneric.di

import com.example.mysocialnetwork.featureDashboard.domain.repository.DashboardRepository
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardViewModel
import com.example.mysocialnetwork.featureLogin.domain.repository.LoginRepository
import com.example.mysocialnetwork.featureLogin.ui.login.LoginViewModel
import com.example.mysocialnetwork.featureLogin.ui.register.RegisterViewModel
import com.example.mysocialnetwork.utilsGeneric.SharedPreferences
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
        LoginRepository(mFirebaseAuth = get(), mFirebaseFirestore = get())
    }

    factory {
        DashboardRepository(mFirebaseFirestore = get())
    }

    single {
        SharedPreferences(androidContext())
    }

}

val viewModels = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }
    viewModel {
        DashboardViewModel(get())
    }
}
