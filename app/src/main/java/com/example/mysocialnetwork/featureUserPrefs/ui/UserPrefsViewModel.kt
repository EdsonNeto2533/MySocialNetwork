package com.example.mysocialnetwork.featureUserPrefs.ui

import android.net.Uri
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

    private val _userImg = MutableLiveData<Uri>()
    val userImg: LiveData<Uri> = _userImg

    fun editUser(mUserPrefsModel: UserPrefsModel) {
        viewModelScope.launch {
            mUserPrefsRepository.editUser(mUserPrefsModel)
        }
    }

    fun updatePosts(list: List<PostModelUserPrefs>){
        viewModelScope.launch {
            mUserPrefsRepository.updatePosts(list){
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

    fun uploadImgToFirebase(img: String, imgUri: Uri){
        viewModelScope.launch {
            mUserPrefsRepository.uploadImgToFirebase(img, imgUri){
                _userImg.value = it
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