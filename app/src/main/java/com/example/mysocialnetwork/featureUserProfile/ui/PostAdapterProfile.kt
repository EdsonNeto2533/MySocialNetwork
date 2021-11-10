package com.example.mysocialnetwork.featureUserProfile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.InputPostBinding
import com.example.mysocialnetwork.databinding.PostModelBinding
import com.example.mysocialnetwork.featureDashboard.domain.entity.PostModel
import com.example.mysocialnetwork.featureDashboard.domain.interfaces.PostClick
import com.example.mysocialnetwork.featureUserProfile.domain.entity.PostModelProfile
import java.util.zip.Inflater

class PostAdapterProfile : ListAdapter<PostModelProfile, ViewHolderPostsProfile>(DiffUtilsPosts()) {
    private val postList = mutableListOf<PostModelProfile>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPostsProfile {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_model, parent, false)
        return ViewHolderPostsProfile(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPostsProfile, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    fun update(newList: List<PostModelProfile>){
        postList.clear()
        postList.addAll(newList)
        submitList(postList.toMutableList())
    }
}


class DiffUtilsPosts() : DiffUtil.ItemCallback<PostModelProfile>() {
    override fun areItemsTheSame(oldItem: PostModelProfile, newItem: PostModelProfile): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: PostModelProfile, newItem: PostModelProfile): Boolean {
        return oldItem == newItem
    }

}


class ViewHolderPostsProfile(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = PostModelBinding.bind(itemView)

    fun bind(postModel: PostModelProfile) {
        binding.tvNameOwner.text = postModel.postOwnerName
        binding.tvPostText.text = postModel.postText
        Glide.with(binding.root).load(postModel.getImg()).into(binding.ivPostOwner)
    }

}