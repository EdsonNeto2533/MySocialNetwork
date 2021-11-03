package com.example.mysocialnetwork.featureLogin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.featureLogin.ui.login.LoginFragment
import com.example.mysocialnetwork.utilsGeneric.changeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            changeFragment(LoginFragment.newInstance())
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            finish()
        else supportFragmentManager.popBackStack()
    }
}