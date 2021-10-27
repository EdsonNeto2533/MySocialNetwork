package com.example.mysocialnetwork.featureLogin.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.featureLogin.domain.entity.UserModel
import com.example.mysocialnetwork.featureLogin.domain.repository.LoginRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel(private val loginRepository: LoginRepository) : ViewModel() {


    private val _userCreated = MutableLiveData<Boolean>()
    val userCreated: LiveData<Boolean> = _userCreated

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error


    fun createUser(email: String, password: String, name: String, gender: String, age: Int) {
        viewModelScope.launch {
            try {
                val id = loginRepository.createUserWithEmailPassword(email, password)
                if (id?.user != null) {
                    loginRepository.createUserInDatabase(
                        UserModel(
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