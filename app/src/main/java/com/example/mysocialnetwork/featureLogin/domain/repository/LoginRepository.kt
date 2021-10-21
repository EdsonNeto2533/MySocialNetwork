package com.example.mysocialnetwork.featureLogin.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class LoginRepository(private val mFirebaseAuth: FirebaseAuth) {


    suspend fun loginWithEmailPassword(email: String, password: String): AuthResult? {
        return mFirebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun createUserWithEmailPassword(email: String, password: String): AuthResult?{
        return mFirebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }


}