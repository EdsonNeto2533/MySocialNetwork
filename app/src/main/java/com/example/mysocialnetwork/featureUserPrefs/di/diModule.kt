package com.example.mysocialnetwork.featureUserPrefs.di

import com.example.mysocialnetwork.featureUserPrefs.domain.repository.UserPrefsRepository
import com.example.mysocialnetwork.featureUserPrefs.ui.UserPrefsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val diUserPrefs = module {
    factory {
        UserPrefsRepository(mFirebaseFirestore = get(), mFirebaseStorage = get())
    }
}


val userPrefsViewModel = module {
    viewModel {
        UserPrefsViewModel(mUserPrefsRepository = get())
    }
}