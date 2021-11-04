package com.example.mysocialnetwork.featureDashboard.domain.repository

import com.example.mysocialnetwork.featureDashboard.domain.entity.UserDashboardModel
import com.example.mysocialnetwork.generics.utils.UserKeysEnum
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DashboardRepository(private val mFirebaseFirestore: FirebaseFirestore) {

    suspend fun getUserDetails(userId: String): UserDashboardModel? {
        var userDashboardModel: UserDashboardModel? = null
        val task = mFirebaseFirestore.collection("table_user").whereEqualTo(UserKeysEnum.USERID.key, userId).get()
        val response = task.await()
        response.forEach {
            userDashboardModel = UserDashboardModel(
                id = it.id,
                email = it.data[UserKeysEnum.USEREMAIL.key] as String,
                name = it.data[UserKeysEnum.USERNAME.key] as String,
                age = it.data[UserKeysEnum.USERAGE.key] as Int,
                gender = it.data[UserKeysEnum.USERGENDER.key] as String
            )
        }
        return userDashboardModel
    }
}