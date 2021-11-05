package com.example.mysocialnetwork.featureDashboard.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.DashboardFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashboardFragment : Fragment(R.layout.dashboard_fragment) {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private val viewModel: DashboardViewModel by viewModel()
    private lateinit var binding: DashboardFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DashboardFragmentBinding.bind(view)
    }


}