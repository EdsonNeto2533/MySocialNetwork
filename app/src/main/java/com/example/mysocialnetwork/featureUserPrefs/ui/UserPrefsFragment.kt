package com.example.mysocialnetwork.featureUserPrefs.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.UserPrefsFragmentBinding
import com.example.mysocialnetwork.featureUserPrefs.domain.entity.PostModelUserPrefs
import com.example.mysocialnetwork.featureUserPrefs.domain.entity.UserPrefsModel
import com.example.mysocialnetwork.featureUserPrefs.domain.interfaces.ClickUserPrefs
import com.example.mysocialnetwork.generics.utils.SharedPreferences
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPrefsFragment : Fragment(R.layout.user_prefs_fragment), ClickUserPrefs {


    private val viewModel: UserPrefsViewModel by viewModel()
    private lateinit var binding: UserPrefsFragmentBinding
    private val sharedPreferences: SharedPreferences by inject()
    private var userLogged: UserPrefsModel? = null
    private val postAdapter = PostAdapterUserPrefs(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UserPrefsFragmentBinding.bind(view)
        loadComponents()
        loadViewModels()
        loadPage()
    }

    private fun loadPage() {
        sharedPreferences.getUserId()?.let {
            viewModel.getPosts(it)
            viewModel.getUserDetails(it)
        }

    }


    private fun loadUserDetails(mUserPrefsModel: UserPrefsModel){
        binding.tvUserName.text = "Nome: ${mUserPrefsModel.name}"
        binding.tvUserEmail.text = "Email: ${mUserPrefsModel.email}"
        binding.tvUserGender.text = "Genero: ${mUserPrefsModel.gender}"
        binding.tvUserAge.text = "Idade: ${mUserPrefsModel.age}"
        Glide.with(requireContext()).load(mUserPrefsModel.getImg()).into(binding.ivUserAvatar)
    }

    private fun loadComponents() {
        binding.rvUserProfilePosts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvUserProfilePosts.adapter = postAdapter

    }


    private fun loadViewModels() {
        viewModel.userLogged.observe(viewLifecycleOwner, {
            userLogged = it
            loadUserDetails(it)
        })
        viewModel.userPostsList.observe(viewLifecycleOwner, {
            postAdapter.update(it)
        })

    }

    override fun clickDelete(mPostModelUserPrefs: PostModelUserPrefs) {
        viewModel.deletePost(mPostModelUserPrefs)
        loadPage()
    }

}