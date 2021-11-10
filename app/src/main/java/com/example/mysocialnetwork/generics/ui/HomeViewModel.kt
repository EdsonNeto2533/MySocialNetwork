package com.example.mysocialnetwork.generics.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.generics.domain.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val mHomeRepository: HomeRepository) : ViewModel() {

    fun logout(){
        viewModelScope.launch {
            mHomeRepository.logout()
        }
    }
}