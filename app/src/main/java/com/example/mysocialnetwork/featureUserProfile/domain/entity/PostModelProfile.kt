package com.example.mysocialnetwork.featureUserProfile.domain.entity

import java.util.Date

data class PostModelProfile(
    val postText: String,
    val postId: String?,
    val postOwnerId: String,
    val postOwnerName: String,
    val postDate: Date
)