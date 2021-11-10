package com.example.mysocialnetwork.featureUserPrefs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.featureUserPrefs.domain.entity.PostModelUserPrefs
import com.example.mysocialnetwork.featureUserPrefs.domain.entity.UserPrefsModel
import com.example.mysocialnetwork.featureUserPrefs.domain.repository.UserPrefsRepository
import kotlinx.coroutines.launch

class UserPrefsViewModel(private val mUserPrefsRepository: UserPrefsRepository) : ViewModel() {

    private val _reload = MutableLiveData<Boolean>()
    val reload: LiveData<Boolean> = _reload

    private val _userLogged = MutableLiveData<UserPrefsModel>()
    val userLogged: LiveData<UserPrefsModel> = _userLogged

    private val _userPostsList = MutableLiveData<List<PostModelUserPrefs>>()
    val userPostsList: LiveData<List<PostModelUserPrefs>> = _userPostsList

    fun editUser(mUserPrefsModel: UserPrefsModel) {
        viewModelScope.launch {
            mUserPrefsRepository.editUser(mUserPrefsModel) {
                _reload.value = true
            }
        }
    }

    fun deletePost(mPostModelUserPrefs: PostModelUserPrefs) {
        viewModelScope.launch {
            mUserPrefsRepository.deletePost(mPostModelUserPrefs) {
                _reload.value = true
            }
        }
    }

    fun getUserDetails(userId: String){
        viewModelScope.launch {
            _userLogged.value = mUserPrefsRepository.getUserDetails(userId)
        }
    }

    fun getPosts(userId: String){
        viewModelScope.launch {
            _userPostsList.value = mUserPrefsRepository.getPostsFromUser(userId)
        }
    }
}