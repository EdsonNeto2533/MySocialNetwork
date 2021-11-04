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
        binding.nvMain.setNavigationItemSelectedListener {

            return@setNavigationItemSelectedListener true
        }

        val toolbar = binding.tbInclude.tbDashboard
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayoutMain,toolbar, R.string.open, R.string.close)
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true

        actionBarDrawerToggle.syncState()






    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }
}