package com.example.mysocialnetwork.featureDashboard.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.featureDashboard.domain.entity.UserDashboardModel
import com.example.mysocialnetwork.featureDashboard.domain.repository.DashboardRepository
import com.example.mysocialnetwork.featureLogin.domain.entity.UserModel
import kotlinx.coroutines.launch

class DashboardViewModel constructor(val mDashboardRepository: DashboardRepository) : ViewModel() {

    private val _userLogged = MutableLiveData<UserDashboardModel>()
    val userLogged: LiveData<UserDashboardModel> = _userLogged

    fun getUserDetails(userId: String){
        viewModelScope.launch {
            _userLogged.value = mDashboardRepository.getUserDetails(userId)
        }
    }
}