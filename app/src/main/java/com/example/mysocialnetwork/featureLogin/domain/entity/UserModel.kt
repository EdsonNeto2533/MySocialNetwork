package com.example.mysocialnetwork.featureLogin.domain.entity

data class UserModel(
    var id: String,
    val name: String,
    val email: String,
    val gender: String,
    val age: Int
)