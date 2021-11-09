package com.example.mysocialnetwork.featureDashboard.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.DashboardFragmentBinding
import com.example.mysocialnetwork.featureDashboard.domain.entity.PostModel
import com.example.mysocialnetwork.featureDashboard.domain.entity.UserDashboardModel
import com.example.mysocialnetwork.featureDashboard.domain.interfaces.PostClick
import com.example.mysocialnetwork.featureDashboard.ui.adapters.PostAdapter
import com.example.mysocialnetwork.generics.utils.SharedPreferences
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar


class DashboardFragment : Fragment(R.layout.dashboard_fragment), PostClick {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private val viewModel: DashboardViewModel by viewModel()
    private val mSharedPreferences: SharedPreferences by inject()
    private lateinit var userLogged: UserDashboardModel
    private val mPostAdapter = PostAdapter(this)
    private lateinit var binding: DashboardFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DashboardFragmentBinding.bind(view)
        loadComponents()
        loadObservers()
        loadPage()
    }

    private fun loadPage() {
        viewModel.getPosts()
        mSharedPreferences.getUserId()?.let { viewModel.getUserDetails(it) }
    }

    private fun loadComponents() {
        binding.rvDashboard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvDashboard.adapter = mPostAdapter
        binding.inputPost.btnAddPost.setOnClickListener {
            newPost()
        }
        binding.inputPost.btnCancelPost.setOnClickListener {
            binding.inputPost.etAddPost.setText("")
        }
    }

    private fun newPost() {
        viewModel.addPost(
            PostModel(
                postId = null,
                postText = binding.inputPost.etAddPost.text.toString(),
                postOwnerName = userLogged.name,
                postOwnerId = userLogged.id,
                postDate = Calendar.getInstance().time
            )
        )
        loadPage()
    }

    private fun loadObservers() {
        viewModel.postList.observe(viewLifecycleOwner, { postList ->
            mPostAdapter.update(postList.sortedBy { it.postDate }.reversed())
        })
        viewModel.userLogged.observe(viewLifecycleOwner, {
            userLogged = it
        })
        viewModel.error.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireContext(), getString(R.string.error_msg_generic), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun profileClick(mPostModel: PostModel) {
        TODO("Not yet implemented")
    }


}