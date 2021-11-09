package com.example.mysocialnetwork.generics.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mysocialnetwork.R


fun AppCompatActivity.changeFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(null).commit()
}


fun AppCompatActivity.changeFragment2(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container2, fragment)
        .addToBackStack(null).commit()
}

