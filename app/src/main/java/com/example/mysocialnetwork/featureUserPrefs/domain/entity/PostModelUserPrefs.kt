package com.example.mysocialnetwork.featureUserPrefs.domain.entity

import java.util.Date

data class PostModelUserPrefs(
    val postText: String,
    val postId: String?,
    val postOwnerId: String,
    val postOwnerName: String,
    val postDate: Date,
    val ownerImg: String?
){
    fun getImg(): String{
        if (ownerImg == null)
            return "https://firebasestorage.googleapis.com/v0/b/my-social-network-d4ed8.appspot.com/o/pngwing.com.png?alt=media&token=0c53ca91-0e1f-4e3b-ad16-b030b6c156ea"
        return ownerImg
    }
}

