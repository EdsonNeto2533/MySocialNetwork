package com.example.mysocialnetwork.featureDashboard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardFragment
import com.example.mysocialnetwork.utilsGeneric.changeFragment

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        if (savedInstanceState == null) {
            changeFragment(DashboardFragment.newInstance())
        }



    }
}