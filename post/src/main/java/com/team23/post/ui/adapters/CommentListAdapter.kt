package com.team23.post.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team23.post.R
import com.team23.post.ui.viewobjects.CommentVO

class CommentListAdapter :
    ListAdapter<CommentVO, CommentListAdapter.PostViewHolder>(PostDiffCallBack()) {
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userPicture: ImageView = itemView.findViewById(R.id.comment_user_picture)
        private val userName: TextView = itemView.findViewById(R.id.comment_username)
        private val commentDuration: TextView = itemView.findViewById(R.id.comment_duration)
        private val commentText: TextView = itemView.findViewById(R.id.comment_text)

        fun bind(comment: CommentVO) {
            userPicture.apply {
                this.setImageBitmap(comment.userPicture)
                this.setOnClickListener {
                    onUserClick?.invoke(comment.userId)
                }
            }
            userName.text = comment.username
            commentDuration.text = comment.duration
            commentText.text = comment.text
        }
    }

    var onUserClick: ((String) -> Unit)? = null

    private class PostDiffCallBack : DiffUtil.ItemCallback<CommentVO>() {
        override fun areContentsTheSame(oldItem: CommentVO, newItem: CommentVO) = oldItem == newItem

        override fun areItemsTheSame(oldItem: CommentVO, newItem: CommentVO) = oldItem == newItem
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return PostViewHolder(itemView)
    }
}
