package com.example.mysocialnetwork.featureDashboard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.InputPostBinding
import com.example.mysocialnetwork.databinding.PostModelBinding
import com.example.mysocialnetwork.featureDashboard.domain.entity.PostModel
import java.util.zip.Inflater

class PostAdapter : ListAdapter<PostModel, ViewHolderPosts>(DiffUtilsPosts()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPosts {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_model, parent, false)
        return ViewHolderPosts(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPosts, position: Int) {
        val post = getItem(position)
        holder.bind(post)
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
        binding.tvPostOwner.text = postModel.postOwnerName
        binding.tvPostText.text = postModel.postText
    }

}