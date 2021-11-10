package com.example.mysocialnetwork.featureDashboard.domain.repository

import com.example.mysocialnetwork.featureDashboard.domain.entity.PostModel
import com.example.mysocialnetwork.featureDashboard.domain.entity.UserDashboardModel
import com.example.mysocialnetwork.generics.utils.PostKeysEnum
import com.example.mysocialnetwork.generics.utils.UserKeysEnum
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DashboardRepository(private val mFirebaseFirestore: FirebaseFirestore) {

    suspend fun getUserDetails(userId: String): UserDashboardModel? {
        var userDashboardModel: UserDashboardModel? = null
        val task = mFirebaseFirestore.collection("table_user").whereEqualTo(UserKeysEnum.USERID.key, userId).get()
        val response = task.await()
        response.forEach {
            userDashboardModel = UserDashboardModel(
                id = it.data[UserKeysEnum.USERID.key] as String,
                email = it.data[UserKeysEnum.USEREMAIL.key] as String,
                name = it.data[UserKeysEnum.USERNAME.key] as String,
                age = (it.data[UserKeysEnum.USERAGE.key] as Long).toInt(),
                gender = it.data[UserKeysEnum.USERGENDER.key] as String,
                userImg = it.data[UserKeysEnum.USERIMG.key] as? String?
            )
        }
        return userDashboardModel
    }

    suspend fun getPosts(): List<PostModel> {
        val postList = mutableListOf<PostModel>()
        val task = mFirebaseFirestore.collection("table_post").get()
        val response = task.await()
        response.forEach {
            val datePost = it.data[PostKeysEnum.POSTDATE.key] as Timestamp
            postList.add(
                PostModel(
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

    suspend fun addPost(postModel: PostModel){
        val map = mutableMapOf<String, Any>()
        map[PostKeysEnum.POSTOWNERNAME.key] = postModel.postOwnerName
        map[PostKeysEnum.POSTTEXT.key] = postModel.postText
        map[PostKeysEnum.POSTOWNERID.key] = postModel.postOwnerId
        map[PostKeysEnum.POSTDATE.key] = postModel.postDate
        mFirebaseFirestore.collection("table_post").add(map).await()
    }
}