package com.team23.post.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team23.post.R

class TagsListAdapter :
    ListAdapter<String, TagsListAdapter.TagsViewHolder>(TagDiffCallback())
    {
        inner class TagsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val textTag: TextView = itemView.findViewById(R.id.text_tag)

            fun bind(tag: String) {
                textTag.text = tag
            }
        }

        private class TagDiffCallback : DiffUtil.ItemCallback<String>() {
            override fun areContentsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem
        }

        override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
            val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_tag, parent, false)
            return TagsViewHolder(itemView)
        }
}