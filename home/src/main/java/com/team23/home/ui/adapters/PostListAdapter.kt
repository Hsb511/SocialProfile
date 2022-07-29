package com.team23.home.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team23.home.R
import com.team23.home.domain.models.PostModel

class PostListAdapter: ListAdapter<PostModel, PostListAdapter.PostViewHolder>(PostDiffCallBack()) {
    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val userPicture: ImageView = itemView.findViewById(R.id.user_picture)
        private val userName: TextView = itemView.findViewById(R.id.user_name)
        private val postDate: TextView = itemView.findViewById(R.id.post_date)
        private val postImage: ImageView = itemView.findViewById(R.id.post_image)
        private val postDescription: TextView = itemView.findViewById(R.id.post_description)

        fun bind(post: PostModel) {
            userPicture.apply {
                this.setImageBitmap(post.ownerPicture)
                this.setOnClickListener {
                    onUserClick?.invoke(post.ownerId)
                }
            }
            userName.text = post.ownerName
            postDate.text = post.publishDate
            postImage.apply {
                this.setImageBitmap(post.image)
                this.setOnClickListener {
                    onPostClick?.invoke(post.id)
                }
            }
            postDescription.text = post.text
        }
    }

    var onUserClick: ((String) -> Unit)? = null
    var onPostClick: ((String) -> Unit)? = null

    private class PostDiffCallBack: DiffUtil.ItemCallback<PostModel>() {
        override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel) = oldItem == newItem

        override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel) = oldItem == newItem
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(itemView)
    }
}
