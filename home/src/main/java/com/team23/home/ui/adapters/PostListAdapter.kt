package com.team23.home.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.team23.home.R
import com.team23.home.domain.models.PostModel

class PostListAdapter : ListAdapter<PostModel, PostListAdapter.PostViewHolder>(PostDiffCallBack()) {
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userPicture: ImageView = itemView.findViewById(R.id.user_picture)
        private val userName: TextView = itemView.findViewById(R.id.user_name)
        private val postDate: TextView = itemView.findViewById(R.id.post_date)
        private val postImage: ImageView = itemView.findViewById(R.id.post_image)
        private val postDescription: TextView = itemView.findViewById(R.id.post_description)

        fun bind(post: PostModel) {
            userPicture.let {
                it.load(post.ownerPictureUrl.toUri().buildUpon().scheme("https").build())
                it.setOnClickListener {
                    onUserClick?.invoke(post.ownerId)
                }
            }
            userName.text = post.ownerName
            postDate.text = post.publishDate
            postImage.let {
                it.load(post.imageUrl.toUri().buildUpon().scheme("https").build())
                it.setOnClickListener {
                    onPostClick?.invoke(post.id)
                }
            }
            postDescription.text = post.text
        }
    }

    var onUserClick: ((String) -> Unit)? = null
    var onPostClick: ((String) -> Unit)? = null

    private class PostDiffCallBack : DiffUtil.ItemCallback<PostModel>() {
        /**
         * Called to check whether two objects represent the same item.
         * For example, if your items have unique ids, this method should check their id equality.
         */
        override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel) =
            oldItem.id == newItem.id

        /**
         * Called to check whether two items have the same data.
         * For example, if you are using DiffUtil with a RecyclerView.Adapter, you should
         * return whether the items' visual representations are the same.
         */
        override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel) =
            oldItem.ownerPictureUrl == newItem.ownerPictureUrl &&
                    oldItem.ownerName == newItem.ownerName &&
                    oldItem.publishDate == newItem.publishDate &&
                    oldItem.imageUrl == newItem.imageUrl &&
                    oldItem.text == newItem.text


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
