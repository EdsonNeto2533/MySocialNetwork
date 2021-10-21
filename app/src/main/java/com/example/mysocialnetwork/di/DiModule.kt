package com.example.mysocialnetwork.di

import com.example.mysocialnetwork.featureLogin.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val domain = module {
    single {
        FirebaseAuth.getInstance()
    }

    factory {
        LoginRepository(get())
    }

}