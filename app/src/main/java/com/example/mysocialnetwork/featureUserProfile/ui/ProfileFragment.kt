package com.example.mysocialnetwork.featureUserProfile.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.ProfileFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment(userId: String) : Fragment(R.layout.profile_fragment) {



    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding: ProfileFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProfileFragmentBinding.bind(view)


    }

}