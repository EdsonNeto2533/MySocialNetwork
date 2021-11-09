package com.example.mysocialnetwork.featureDashboard.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.featureDashboard.domain.entity.PostModel
import com.example.mysocialnetwork.featureDashboard.domain.entity.UserDashboardModel
import com.example.mysocialnetwork.featureDashboard.domain.repository.DashboardRepository
import com.example.mysocialnetwork.featureLogin.domain.entity.UserModel
import kotlinx.coroutines.launch
import java.lang.Exception

class DashboardViewModel constructor(private val mDashboardRepository: DashboardRepository) : ViewModel() {

    private val _userLogged = MutableLiveData<UserDashboardModel>()
    val userLogged: LiveData<UserDashboardModel> = _userLogged

    private val _postList = MutableLiveData<List<PostModel>>()
    val postList: LiveData<List<PostModel>> = _postList

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun getUserDetails(userId: String) {
        viewModelScope.launch {
            _userLogged.value = mDashboardRepository.getUserDetails(userId)
        }
    }

    fun getPosts() {
        viewModelScope.launch {
            try {
                _postList.value = mDashboardRepository.getPosts()
            } catch (e: Exception) {
                _error.value = true
            }
        }
    }

    fun addPost(postModel: PostModel) {
        viewModelScope.launch {
            try {
                mDashboardRepository.addPost(postModel)
            } catch (e: Exception) {
                _error.value = true
            }
        }
    }


}