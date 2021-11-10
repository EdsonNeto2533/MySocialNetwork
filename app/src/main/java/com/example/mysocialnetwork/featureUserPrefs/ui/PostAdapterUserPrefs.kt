package com.example.mysocialnetwork.featureUserPrefs.ui

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
import com.example.mysocialnetwork.databinding.PostModelUserPrefsBinding
import com.example.mysocialnetwork.featureDashboard.domain.entity.PostModel
import com.example.mysocialnetwork.featureDashboard.domain.interfaces.PostClick
import com.example.mysocialnetwork.featureUserPrefs.domain.entity.PostModelUserPrefs
import com.example.mysocialnetwork.featureUserPrefs.domain.interfaces.ClickUserPrefs
import java.util.zip.Inflater

class PostAdapterUserPrefs(val mClickUserPrefs: ClickUserPrefs) : ListAdapter<PostModelUserPrefs, ViewHolderPostsUserPrefs>(DiffUtilsPosts()) {
    private val postList = mutableListOf<PostModelUserPrefs>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPostsUserPrefs {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_model, parent, false)
        return ViewHolderPostsUserPrefs(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPostsUserPrefs, position: Int) {
        val post = getItem(position)
        holder.bind(post)
        holder.ibDelete.setOnClickListener {
            mClickUserPrefs.clickDelete(post)
        }
    }

    fun update(newList: List<PostModelUserPrefs>){
        postList.clear()
        postList.addAll(newList)
        submitList(postList.toMutableList())
    }
}


class DiffUtilsPosts() : DiffUtil.ItemCallback<PostModelUserPrefs>() {
    override fun areItemsTheSame(oldItem: PostModelUserPrefs, newItem: PostModelUserPrefs): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: PostModelUserPrefs, newItem: PostModelUserPrefs): Boolean {
        return oldItem == newItem
    }

}


class ViewHolderPostsUserPrefs(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = PostModelUserPrefsBinding.bind(itemView)
    val ibDelete = binding.ibDelete

    fun bind(mPostModelUserPrefs: PostModelUserPrefs) {
        binding.tvNameOwner.text = mPostModelUserPrefs.postOwnerName
        binding.tvPostText.text = mPostModelUserPrefs.postText
        Glide.with(binding.root).load(mPostModelUserPrefs.getImg()).into(binding.ivPostOwner)
    }

}