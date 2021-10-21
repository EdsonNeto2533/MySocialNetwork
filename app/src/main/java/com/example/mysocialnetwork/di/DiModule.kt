package com.example.mysocialnetwork.di

import com.example.mysocialnetwork.featureLogin.domain.repository.LoginRepository
import com.example.mysocialnetwork.featureLogin.ui.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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

}

val viewModels = module {
    viewModel {
        LoginViewModel(get())
    }
}
