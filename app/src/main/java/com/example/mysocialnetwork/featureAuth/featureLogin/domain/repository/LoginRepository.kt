package com.example.mysocialnetwork.featureAuth.featureLogin.domain.repository

import com.example.mysocialnetwork.featureAuth.featureLogin.domain.entity.UserModelLogin
import com.example.mysocialnetwork.generics.utils.UserKeysEnum
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoginRepository(private val mFirebaseAuth: FirebaseAuth, private val mFirebaseFirestore: FirebaseFirestore) {


    suspend fun loginWithEmailPassword(email: String, password: String): AuthResult? {
        return mFirebaseAuth.signInWithEmailAndPassword(email, password).await()
    }



}