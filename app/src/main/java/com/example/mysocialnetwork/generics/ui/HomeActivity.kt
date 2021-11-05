package com.example.mysocialnetwork.generics.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.bumptech.glide.Glide
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.DrawerHeaderBinding
import com.example.mysocialnetwork.databinding.HomeActivityBinding
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardFragment
import com.example.mysocialnetwork.featureDashboard.ui.dashboard.DashboardViewModel
import com.example.mysocialnetwork.generics.utils.SharedPreferences
import com.example.mysocialnetwork.generics.utils.changeFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding
    private val viewModel: DashboardViewModel by viewModel()
    private val sharedPreferences: SharedPreferences by inject()

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
        sharedPreferences.getUserId()?.let { viewModel.getUserDetails(it) }
    }

    private fun loadViewModels(){
        viewModel.userLogged.observe(this, {
            binding.nvMain.getHeaderView(0).apply {
                val headerBinding = DrawerHeaderBinding.bind(this)
                headerBinding.tvUserEmail.text = it.email
                headerBinding.tvUserName.text = it.name
                if (it.userImg != null) Glide.with(this).load(it.userImg).into(headerBinding.ivUserAvatar)
                else Glide.with(this).load(R.drawable.ic_home).into(headerBinding.ivUserAvatar)
            }
        })
    }


    private fun setupActionBar(){
        val toolbar = binding.tbInclude.tbDashboard
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayoutMain,toolbar, R.string.open, R.string.close)
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true

        actionBarDrawerToggle.syncState()

    }


    private fun setupDrawerListener(){
        binding.nvMain.setNavigationItemSelectedListener {

            return@setNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }
}