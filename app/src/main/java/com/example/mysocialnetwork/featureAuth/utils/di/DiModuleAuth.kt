package com.example.mysocialnetwork.featureAuth.utils.di

import com.example.mysocialnetwork.featureAuth.featureLogin.domain.repository.LoginRepository
import com.example.mysocialnetwork.featureAuth.featureLogin.ui.LoginViewModel
import com.example.mysocialnetwork.featureAuth.featureRegister.domain.repository.RegisterRepository
import com.example.mysocialnetwork.featureAuth.featureRegister.ui.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diAuth = module {
    factory {
        LoginRepository(mFirebaseAuth = get(), mFirebaseFirestore = get())
    }
    factory {
        RegisterRepository(mFirebaseFirestore = get(), mFirebaseAuth = get())
    }
}


val viewModelsAuth = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }
}