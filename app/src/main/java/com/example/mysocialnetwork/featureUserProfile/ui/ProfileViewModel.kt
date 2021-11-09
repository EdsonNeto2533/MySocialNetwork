package com.example.mysocialnetwork.featureUserProfile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.featureUserProfile.domain.entity.PostModelProfile
import com.example.mysocialnetwork.featureUserProfile.domain.entity.UserModelProfile
import com.example.mysocialnetwork.featureUserProfile.domain.repository.UserProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val mUserProfileRepository: UserProfileRepository) : ViewModel() {

    private val _user = MutableLiveData<UserModelProfile>()
    val user: LiveData<UserModelProfile> = _user

    private val _postList = MutableLiveData<List<PostModelProfile>>()
    val postList: LiveData<List<PostModelProfile>> = _postList

    fun getUserDetails(userId: String){
        viewModelScope.launch {
            _user.value = mUserProfileRepository.getUserDetails(userId)
        }
    }


    fun getPostList(userId: String){
        viewModelScope.launch {
            _postList.value = mUserProfileRepository.getPostsFromUser(userId)
        }
    }

}