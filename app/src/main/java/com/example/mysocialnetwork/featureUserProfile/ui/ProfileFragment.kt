package com.example.mysocialnetwork.featureUserProfile.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.ProfileFragmentBinding
import com.example.mysocialnetwork.featureUserProfile.domain.entity.UserModelProfile
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment(val userId: String) : Fragment(R.layout.profile_fragment) {
    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding: ProfileFragmentBinding
    private val adapterProfile = PostAdapterProfile()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProfileFragmentBinding.bind(view)
        loadComponents()
        loadObservers()
        viewModel.getPostList(userId)
        viewModel.getUserDetails(userId)
    }

    private fun loadObservers() {
        viewModel.postList.observe(viewLifecycleOwner, {
            adapterProfile.update(it)
        })
        viewModel.user.observe(viewLifecycleOwner, {
            loadUser(it)
        })
    }

    private fun loadUser(mUserModelProfile: UserModelProfile) {
        binding.tvUserName.text = mUserModelProfile.name
        binding.tvUserEmail.text = mUserModelProfile.email
        binding.tvUserGender.text = mUserModelProfile.gender
        binding.tvUserAge.text = mUserModelProfile.age.toString()
    }

    private fun loadComponents() {
        binding.rvUserProfilePosts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvUserProfilePosts.adapter = adapterProfile
    }

}