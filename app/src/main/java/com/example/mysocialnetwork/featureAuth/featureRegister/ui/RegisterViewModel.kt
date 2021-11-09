package com.example.mysocialnetwork.featureAuth.featureRegister.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.featureAuth.featureLogin.domain.entity.UserModelLogin
import com.example.mysocialnetwork.featureAuth.featureLogin.domain.repository.LoginRepository
import com.example.mysocialnetwork.featureAuth.featureRegister.domain.entity.UserModelRegister
import com.example.mysocialnetwork.featureAuth.featureRegister.domain.repository.RegisterRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel(private val mRegisterRepository: RegisterRepository) : ViewModel() {


    private val _userCreated = MutableLiveData<Boolean>()
    val userCreated: LiveData<Boolean> = _userCreated

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error


    fun createUser(email: String, password: String, name: String, gender: String, age: Int) {
        viewModelScope.launch {
            try {
                val id = mRegisterRepository.createUserWithEmailPassword(email, password)
                if (id?.user != null) {
                    mRegisterRepository.createUserInDatabase(
                        UserModelRegister(
                            id.user!!.uid,
                            email = email, name = name, gender = gender, age = age
                        )
                    )
                    _userCreated.value = true
                }
            } catch (e: Exception) {
                _error.value = true
            }


        }

    }
}