package com.example.mysocialnetwork.featureUserProfile.di

import com.example.mysocialnetwork.featureUserProfile.domain.repository.UserProfileRepository
import com.example.mysocialnetwork.featureUserProfile.ui.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diProfile = module {
    factory {
        UserProfileRepository(mFirebaseFirestore = get())
    }
}

val viewModelProfile = module {
    viewModel {
        ProfileViewModel(mUserProfileRepository = get())
    }
}