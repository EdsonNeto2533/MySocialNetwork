package com.example.mysocialnetwork.generics.utils

import android.content.Context
import androidx.core.content.edit

class SharedPreferences(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(SPKeysEnum.MAINSHARED.key, Context.MODE_PRIVATE)


    fun setUserId(userId: String){
        sharedPreferences.edit {
            putString(SPKeysEnum.USERLOGGED.key, userId)
        }
    }

    fun setRememberMe(remember: Boolean){
        sharedPreferences.edit{
            putBoolean(SPKeysEnum.REMEMBERME.key, remember)
        }
    }

    fun getRememberMe(): Boolean{
        return sharedPreferences.getBoolean(SPKeysEnum.REMEMBERME.key, false)
    }

    fun getUserId(): String?{
        return sharedPreferences.getString(SPKeysEnum.USERLOGGED.key, "")
    }
}