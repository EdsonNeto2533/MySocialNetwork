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
import com.example.mysocialnetwork.generics.ui.HomeActivity
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


    private fun loadUserDetails(mUserPrefsModel: UserPrefsModel) {
        binding.etUserName.setText(mUserPrefsModel.name)
        binding.etUserEmail.setText(mUserPrefsModel.email)
        binding.etUserGender.setText(mUserPrefsModel.gender)
        binding.etUserAge.setText(mUserPrefsModel.age.toString())
        Glide.with(requireContext()).load(mUserPrefsModel.getImg()).into(binding.ivUserAvatar)
    }

    private fun loadComponents() {
        binding.rvUserProfilePosts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvUserProfilePosts.adapter = postAdapter
        binding.btnEdit.setOnClickListener {
            enableOrDisableFields(true)
            binding.btnSave.visibility = View.VISIBLE
        }
        binding.btnSave.setOnClickListener {
            updateUser()
            binding.btnSave.visibility = View.INVISIBLE
            enableOrDisableFields(false)
        }
    }

    private fun updateUser(){
        userLogged?.apply {
            this.name = binding.etUserName.text.toString()
            this.age = binding.etUserAge.text.toString().toInt()
            this.gender = binding.etUserGender.text.toString()
            val postList = postAdapter.getPosts()
            postList.forEach { post ->
                post.postOwnerName = this.name
                post.ownerImg = this.userImg
            }

            viewModel.editUser(this)
            viewModel.updatePosts(postList)
        }
    }

    private fun enableOrDisableFields(action: Boolean){
        binding.etUserName.isEnabled = action
        binding.etUserGender.isEnabled = action
        binding.etUserAge.isEnabled = action
    }


    private fun loadViewModels() {
        viewModel.userLogged.observe(viewLifecycleOwner, {
            userLogged = it
            loadUserDetails(it)
        })
        viewModel.userPostsList.observe(viewLifecycleOwner, {
            postAdapter.update(it)
        })
        viewModel.reload.observe(viewLifecycleOwner, {
            loadPage()
            (requireActivity() as HomeActivity).loadUser()
        })

    }

    override fun clickDelete(mPostModelUserPrefs: PostModelUserPrefs) {
        viewModel.deletePost(mPostModelUserPrefs)
    }

}