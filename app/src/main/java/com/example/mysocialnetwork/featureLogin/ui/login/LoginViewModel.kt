package com.example.mysocialnetwork.featureLogin.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.featureLogin.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel constructor(private val mRepository: LoginRepository) : ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _userLogged = MutableLiveData<FirebaseUser>()
    val userLogged: LiveData<FirebaseUser> = _userLogged


    fun loginWithEmailPassword(email: String, password: String) {
        viewModelScope.launch {
            try {
                _userLogged.value = mRepository.loginWithEmailPassword(email, password)?.user
            } catch (e: Exception){
                   _error.value = true
            }
        }
    }

}