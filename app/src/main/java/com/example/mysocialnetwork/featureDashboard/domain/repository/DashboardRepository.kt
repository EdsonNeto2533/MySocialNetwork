package com.example.mysocialnetwork.featureDashboard.domain.repository

import com.example.mysocialnetwork.featureLogin.domain.entity.UserModel
import com.example.mysocialnetwork.utilsGeneric.UserKeysEnum
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DashboardRepository(private val mFirebaseFirestore: FirebaseFirestore) {

    suspend fun getUserDetails(userId: String): UserModel? {
        var userModel: UserModel? = null
        val task = mFirebaseFirestore.collection("table_user").whereEqualTo(UserKeysEnum.USERID.key, userId).get()
        val response = task.await()
        response.forEach {
            userModel = UserModel(
                id = it.id,
                email = it.data[UserKeysEnum.USEREMAIL.key] as String,
                name = it.data[UserKeysEnum.USERNAME.key] as String,
                age = it.data[UserKeysEnum.USERAGE.key] as Int,
                gender = it.data[UserKeysEnum.USERGENDER.key] as String
            )
        }
        return userModel
    }
}