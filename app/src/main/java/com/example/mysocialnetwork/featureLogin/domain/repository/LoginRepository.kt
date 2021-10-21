package com.example.mysocialnetwork.featureLogin.domain.repository

import com.example.mysocialnetwork.featureLogin.domain.entity.UserModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoginRepository(private val mFirebaseAuth: FirebaseAuth, private val mFirebaseFirestore: FirebaseFirestore) {


    suspend fun loginWithEmailPassword(email: String, password: String): AuthResult? {
        return mFirebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun createUserWithEmailPassword(email: String, password: String): AuthResult?{
        return mFirebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }


    suspend fun createUserInDatabase(mUserModel: UserModel){

    }


}