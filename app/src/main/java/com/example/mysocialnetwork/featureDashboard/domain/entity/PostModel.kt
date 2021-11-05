package com.example.mysocialnetwork.featureDashboard.domain.entity

data class PostModel(
    val postText: String,
    val postId: String?,
    val postOwnerId: String,
    val postOwnerName: String
)

