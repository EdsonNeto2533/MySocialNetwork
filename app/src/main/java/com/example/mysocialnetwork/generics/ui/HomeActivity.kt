package com.example.mysocialnetwork.generics.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.bumptech.glide.Glide
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.DrawerHeaderBinding
import com.example.mysocialnetwork.databinding.HomeActivityBinding
import com.example.mysocialnetwork.featureAuth.utils.ui.MainActivity
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardFragment
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardViewModel
import com.example.mysocialnetwork.featureUserPrefs.ui.UserPrefsFragment
import com.example.mysocialnetwork.generics.utils.SharedPreferences
import com.example.mysocialnetwork.generics.utils.changeFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding
    private val viewModel: DashboardViewModel by viewModel()
    private val sharedPreferences: SharedPreferences by inject()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            changeFragment(DashboardFragment.newInstance())
        }
        setupActionBar()
        setupDrawerListener()
        loadViewModels()
        if (sharedPreferences.getUserId().isNullOrBlank()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else
            viewModel.getUserDetails(sharedPreferences.getUserId()!!)
    }

    private fun loadViewModels() {
        viewModel.userLogged.observe(this, {
            binding.nvMain.getHeaderView(0).apply {
                val headerBinding = DrawerHeaderBinding.bind(this)
                headerBinding.tvUserEmail.text = "Email: ${it.email}"
                headerBinding.tvUserName.text = "Nome: ${it.name}"
                Glide.with(this).load(it.getImg()).into(headerBinding.ivUserAvatar)
            }
        })
    }


    private fun setupActionBar() {
        val toolbar = binding.tbInclude.tbDashboard
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayoutMain, toolbar, R.string.open, R.string.close)
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true

        actionBarDrawerToggle.syncState()

    }


    private fun setupDrawerListener() {
        binding.nvMain.setNavigationItemSelectedListener {
            binding.drawerLayoutMain.closeDrawers()
            when (it.itemId) {
                R.id.btn_home -> changeFragment(DashboardFragment.newInstance())
                R.id.btn_logout -> {
                    homeViewModel.logout()
                    sharedPreferences.setUserId("")
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                R.id.btn_user_pref -> changeFragment(UserPrefsFragment())
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            finish()
        else supportFragmentManager.popBackStack()
    }
}