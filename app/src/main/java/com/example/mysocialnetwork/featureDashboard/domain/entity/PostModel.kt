package com.example.mysocialnetwork.featureDashboard.domain.entity

import java.util.Date

data class PostModel(
    val postText: String,
    val postId: String?,
    val postOwnerId: String,
    val postOwnerName: String,
    val postDate: Date
)

