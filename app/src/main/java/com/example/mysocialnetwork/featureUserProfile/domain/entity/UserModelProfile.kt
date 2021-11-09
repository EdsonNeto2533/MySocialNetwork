package com.example.mysocialnetwork.featureUserProfile.domain.entity

data class UserModelProfile(
    var id: String,
    val name: String,
    val email: String,
    val gender: String,
    val age: Int,
    val userImg: String?
)
