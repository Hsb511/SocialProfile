package com.team23.home.ui.adapters

import android.graphics.BitmapFactory
import android.os.StrictMode
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
import java.net.URL
import java.time.format.DateTimeFormatter

class PostListAdapter: ListAdapter<PostModel, PostListAdapter.PostViewHolder>(PostDiffCallBack()) {
    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val userPicture: ImageView = itemView.findViewById(R.id.user_picture)
        private val userName: TextView = itemView.findViewById(R.id.user_name)
        private val postDate: TextView = itemView.findViewById(R.id.post_date)
        private val postImage: ImageView = itemView.findViewById(R.id.post_image)
        private val postDescription: TextView = itemView.findViewById(R.id.post_description)

        fun bind(post: PostModel) {
            // TODO CHANGE THAT FOR COROUTINE !
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            // TODO CHANGE THAT OR AT LEAST FACTORIZE IT
            userPicture.setImageBitmap(BitmapFactory.decodeStream(URL(post.ownerPicture).openConnection().getInputStream()))
            userName.text = post.ownerName
            postDate.text = post.publishDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"))
            postImage.setImageBitmap(BitmapFactory.decodeStream(URL(post.image).openConnection().getInputStream()))
            postDescription.text = post.text
        }
    }

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
