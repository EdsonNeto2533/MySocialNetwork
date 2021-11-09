package com.example.mysocialnetwork.featureAuth.featureRegister.domain.repository

import com.example.mysocialnetwork.featureAuth.featureLogin.domain.entity.UserModelLogin
import com.example.mysocialnetwork.featureAuth.featureRegister.domain.entity.UserModelRegister
import com.example.mysocialnetwork.generics.utils.UserKeysEnum
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RegisterRepository(private val mFirebaseAuth: FirebaseAuth, private val mFirebaseFirestore: FirebaseFirestore) {


    suspend fun createUserWithEmailPassword(email: String, password: String): AuthResult?{
        return mFirebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun createUserInDatabase(mUserModelRegister: UserModelRegister){
        val map = mutableMapOf<String, Any>()
        map[UserKeysEnum.USERID.key] = mUserModelRegister.id
        map[UserKeysEnum.USEREMAIL.key] = mUserModelRegister.email
        map[UserKeysEnum.USERGENDER.key] = mUserModelRegister.gender
        map[UserKeysEnum.USERAGE.key] = mUserModelRegister.age
        map[UserKeysEnum.USERNAME.key] = mUserModelRegister.name

        mFirebaseFirestore.collection("table_user").add(map).await()
    }




}