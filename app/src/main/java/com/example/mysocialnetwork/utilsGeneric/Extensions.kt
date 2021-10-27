package com.example.mysocialnetwork.utilsGeneric

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mysocialnetwork.R


fun AppCompatActivity.changeFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(null).commit()
}


