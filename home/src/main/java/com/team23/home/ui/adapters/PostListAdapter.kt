package com.team23.home.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.team23.home.R
import com.team23.home.ui.viewobjects.PostVO

class PostListAdapter : ListAdapter<PostVO, PostListAdapter.PostViewHolder>(PostDiffCallBack()) {
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostVO) {
            itemView.findViewById<ImageView>(R.id.user_picture).let {
                it.load(post.ownerPictureUri)
                it.setOnClickListener {
                    onUserClick?.invoke(post.ownerId)
                }
            }
            itemView.findViewById<TextView>(R.id.user_name).text = post.ownerName
            itemView.findViewById<TextView>(R.id.post_date).text = post.publishDate
            itemView.findViewById<ImageView>(R.id.post_image).let {
                it.load(post.imageUri)
                it.setOnClickListener {
                    onPostClick?.invoke(post.id)
                }
            }
            itemView.findViewById<TextView>(R.id.post_description).text = post.text
        }
    }

    var onUserClick: ((String) -> Unit)? = null
    var onPostClick: ((String) -> Unit)? = null

    private class PostDiffCallBack : DiffUtil.ItemCallback<PostVO>() {
        /**
         * Called to check whether two objects represent the same item.
         * For example, if your items have unique ids, this method should check their id equality.
         */
        override fun areItemsTheSame(oldItem: PostVO, newItem: PostVO) =
            oldItem.id == newItem.id

        /**
         * Called to check whether two items have the same data.
         * For example, if you are using DiffUtil with a RecyclerView.Adapter, you should
         * return whether the items' visual representations are the same.
         */
        override fun areContentsTheSame(oldItem: PostVO, newItem: PostVO) =
            oldItem.ownerPictureUri == newItem.ownerPictureUri &&
                    oldItem.ownerName == newItem.ownerName &&
                    oldItem.publishDate == newItem.publishDate &&
                    oldItem.imageUri == newItem.imageUri &&
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
