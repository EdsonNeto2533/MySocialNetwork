package com.example.mysocialnetwork.generics.domain

import com.google.firebase.auth.FirebaseAuth

class HomeRepository(private val mFirebaseAuth: FirebaseAuth) {

    suspend fun logout(){
        mFirebaseAuth.signOut()
    }
}