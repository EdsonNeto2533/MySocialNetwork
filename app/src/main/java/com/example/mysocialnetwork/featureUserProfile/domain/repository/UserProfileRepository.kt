package com.example.mysocialnetwork.featureUserProfile.domain.repository

import com.example.mysocialnetwork.featureUserProfile.domain.entity.PostModelProfile
import com.example.mysocialnetwork.featureUserProfile.domain.entity.UserModelProfile
import com.example.mysocialnetwork.generics.utils.PostKeysEnum
import com.example.mysocialnetwork.generics.utils.UserKeysEnum
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserProfileRepository(private val mFirebaseFirestore: FirebaseFirestore) {


    suspend fun getUserDetails(userId: String): UserModelProfile? {
        var user: UserModelProfile? = null
        val task = mFirebaseFirestore.collection("table_user").whereEqualTo(UserKeysEnum.USERID.key, userId).get()
        val response = task.await()
        response.forEach {
            println("alo")
            user = UserModelProfile(
                id = it.id,
                email = it.data[UserKeysEnum.USEREMAIL.key] as String,
                name = it.data[UserKeysEnum.USERNAME.key] as String,
                age = (it.data[UserKeysEnum.USERAGE.key] as Long).toInt(),
                gender = it.data[UserKeysEnum.USERGENDER.key] as String,
                userImg = it.data[UserKeysEnum.USERIMG.key] as? String?
            )
        }
        return user
    }


    suspend fun getPostsFromUser(userId: String): List<PostModelProfile> {
        val postList = mutableListOf<PostModelProfile>()
        val task = mFirebaseFirestore.collection("table_post").whereEqualTo(PostKeysEnum.POSTOWNERID.key, userId).get()
        val response = task.await()
        response.forEach {
            val datePost = it.data[PostKeysEnum.POSTDATE.key] as Timestamp
            postList.add(
                PostModelProfile(
                    postId = it.id,
                    postDate = datePost.toDate(),
                    postOwnerId = it.data[PostKeysEnum.POSTOWNERID.key] as String,
                    postOwnerName = it.data[PostKeysEnum.POSTOWNERNAME.key] as String,
                    postText = it.data[PostKeysEnum.POSTTEXT.key] as String,
                    ownerImg = it.data[PostKeysEnum.POSTOWNERIMG.key] as? String?
                )
            )
        }
        return postList
    }
}