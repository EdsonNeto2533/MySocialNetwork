package com.example.mysocialnetwork.featureUserPrefs.di

import com.example.mysocialnetwork.featureUserPrefs.ui.UserPrefsViewModel
import com.example.mysocialnetwork.featureUserProfile.domain.repository.UserProfileRepository
import org.koin.dsl.module


val diUserPrefs = module {
    factory {
        UserProfileRepository(mFirebaseFirestore = get())
    }
}


val userPrefsViewModel = module {
    factory {
        UserPrefsViewModel(mUserPrefsRepository = get())
    }
}