package com.example.mysocialnetwork.featureAuth.utils.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.featureAuth.featureLogin.ui.LoginFragment
import com.example.mysocialnetwork.generics.utils.changeFragment

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