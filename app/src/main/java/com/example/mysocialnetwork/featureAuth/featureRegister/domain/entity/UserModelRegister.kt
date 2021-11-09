package com.example.mysocialnetwork.featureAuth.featureRegister.domain.entity

data class UserModelRegister(
    var id: String,
    val name: String,
    val email: String,
    val gender: String,
    val age: Int
)