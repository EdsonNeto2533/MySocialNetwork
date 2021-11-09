package com.example.mysocialnetwork.featureAuth.featureLogin.domain.entity

data class UserModelLogin(
    var id: String,
    val name: String,
    val email: String,
    val gender: String,
    val age: Int
)