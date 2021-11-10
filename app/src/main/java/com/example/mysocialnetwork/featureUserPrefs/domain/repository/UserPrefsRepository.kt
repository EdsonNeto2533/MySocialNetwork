package com.example.mysocialnetwork.featureUserPrefs.domain.repository

import com.example.mysocialnetwork.featureUserPrefs.domain.entity.PostModelUserPrefs
import com.example.mysocialnetwork.featureUserPrefs.domain.entity.UserPrefsModel
import com.example.mysocialnetwork.featureUserProfile.domain.entity.PostModelProfile
import com.example.mysocialnetwork.featureUserProfile.domain.entity.UserModelProfile
import com.example.mysocialnetwork.generics.utils.PostKeysEnum
import com.example.mysocialnetwork.generics.utils.UserKeysEnum
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserPrefsRepository(private val mFirebaseFirestore: FirebaseFirestore) {

    suspend fun getUserDetails(userId: String): UserPrefsModel? {
        var user: UserPrefsModel? = null
        val task = mFirebaseFirestore.collection("table_user").whereEqualTo(UserKeysEnum.USERID.key, userId).get()
        val response = task.await()
        response.forEach {
            println("alo")
            user = UserPrefsModel(
                dbId = it.id,
                email = it.data[UserKeysEnum.USEREMAIL.key] as String,
                name = it.data[UserKeysEnum.USERNAME.key] as String,
                age = (it.data[UserKeysEnum.USERAGE.key] as Long).toInt(),
                gender = it.data[UserKeysEnum.USERGENDER.key] as String,
                userImg = it.data[UserKeysEnum.USERIMG.key] as? String?,
                id = it.data[UserKeysEnum.USERID.key] as String
            )
        }
        return user
    }


    suspend fun getPostsFromUser(userId: String): List<PostModelUserPrefs> {
        val postList = mutableListOf<PostModelUserPrefs>()
        val task = mFirebaseFirestore.collection("table_post").whereEqualTo(PostKeysEnum.POSTOWNERID.key, userId).get()
        val response = task.await()
        response.forEach {
            val datePost = it.data[PostKeysEnum.POSTDATE.key] as Timestamp
            postList.add(
                PostModelUserPrefs(
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


    suspend fun deletePost(mPostModelUserPrefs: PostModelUserPrefs) {
        val task = mPostModelUserPrefs.postId?.let { mFirebaseFirestore.collection("table_post").document(it).delete() }
        task?.await()
    }


    suspend fun editUser(mUserPrefsModel: UserPrefsModel) {
        val map = mutableMapOf<String, Any?>()
        map[UserKeysEnum.USERNAME.key] = mUserPrefsModel.name
        map[UserKeysEnum.USERIMG.key] = mUserPrefsModel.userImg
        map[UserKeysEnum.USERAGE.key] = mUserPrefsModel.age
        map[UserKeysEnum.USERGENDER.key] = mUserPrefsModel.gender
        val task = mFirebaseFirestore.collection("table_user").document(mUserPrefsModel.dbId).update(map)
        task.await()
    }

}