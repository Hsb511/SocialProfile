package com.team23.post.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.team23.post.R
import com.team23.post.ui.viewobjects.CommentVO

class CommentListAdapter(
    private val context: Context
) : ListAdapter<CommentVO, CommentListAdapter.PostViewHolder>(PostDiffCallBack()) {
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: CommentVO) {
            itemView.findViewById<ImageView>(R.id.comment_user_picture).let {
                Glide.with(context)
                    .load(comment.userPictureUrl)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(it)
                it.setOnClickListener {
                    onUserClick?.invoke(comment.userId)
                }
            }
            itemView.findViewById<TextView>(R.id.comment_username).text = comment.username
            itemView.findViewById<TextView>(R.id.comment_duration).text = comment.duration
            itemView.findViewById<TextView>(R.id.comment_text).text = comment.text
        }
    }

    var onUserClick: ((String) -> Unit)? = null

    private class PostDiffCallBack : DiffUtil.ItemCallback<CommentVO>() {
        override fun areItemsTheSame(oldComment: CommentVO, newComment: CommentVO) =
            oldComment.id == newComment.id

        override fun areContentsTheSame(oldComment: CommentVO, newComment: CommentVO) =
            oldComment.userPictureUrl == newComment.userPictureUrl &&
                    oldComment.username == newComment.username &&
                    oldComment.duration == newComment.duration &&
                    oldComment.text == newComment.text
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
