package com.example.mysocialnetwork.featureDashboard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.DashboardActivityBinding
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardFragment
import com.example.mysocialnetwork.utilsGeneric.changeFragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: DashboardActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DashboardActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            changeFragment(DashboardFragment.newInstance())
        }

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayoutMain, R.string.open, R.string.close)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        actionBarDrawerToggle.syncState()
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)



    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }
}