package com.example.mysocialnetwork.featureDashboard.di

import com.example.mysocialnetwork.featureDashboard.domain.repository.DashboardRepository
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diDashboard = module {
    factory {
        DashboardRepository(mFirebaseFirestore = get())
    }
}

val viewModelDashboard = module {
    viewModel {
        DashboardViewModel(get())
    }
}