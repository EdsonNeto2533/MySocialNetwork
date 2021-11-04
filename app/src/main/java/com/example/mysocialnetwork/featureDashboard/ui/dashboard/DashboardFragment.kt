package com.example.mysocialnetwork.featureDashboard.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysocialnetwork.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dashboard_activity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}