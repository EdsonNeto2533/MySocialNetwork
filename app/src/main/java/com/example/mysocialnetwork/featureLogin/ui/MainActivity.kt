package com.example.mysocialnetwork.featureLogin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.featureLogin.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}