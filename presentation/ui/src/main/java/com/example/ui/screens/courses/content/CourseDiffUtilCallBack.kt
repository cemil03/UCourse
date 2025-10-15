package com.example.ui.screens.courses.content

import androidx.recyclerview.widget.DiffUtil

object CourseDiffCallback : DiffUtil.ItemCallback<ContentItem>() {
    override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
        return oldItem == newItem
    }
}