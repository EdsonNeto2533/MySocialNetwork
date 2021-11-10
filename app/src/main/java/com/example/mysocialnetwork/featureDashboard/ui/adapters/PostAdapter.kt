package com.example.mysocialnetwork.featureDashboard.ui.adapters

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
import java.util.zip.Inflater

class PostAdapter(val mPostClick: PostClick) : ListAdapter<PostModel, ViewHolderPosts>(DiffUtilsPosts()) {
    private val postList = mutableListOf<PostModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPosts {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_model, parent, false)
        return ViewHolderPosts(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPosts, position: Int) {
        val post = getItem(position)
        holder.bind(post)
        holder.itemView.setOnClickListener {
            mPostClick.profileClick(post)
        }
    }

    fun update(newList: List<PostModel>){
        postList.clear()
        postList.addAll(newList)
        submitList(postList.toMutableList())
    }
}


class DiffUtilsPosts() : DiffUtil.ItemCallback<PostModel>() {
    override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
        return oldItem == newItem
    }

}


class ViewHolderPosts(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = PostModelBinding.bind(itemView)

    fun bind(postModel: PostModel) {
        binding.tvNameOwner.text = postModel.postOwnerName
        binding.tvPostText.text = postModel.postText
        Glide.with(binding.root).load(postModel.getImg()).into(binding.ivPostOwner)
    }

}