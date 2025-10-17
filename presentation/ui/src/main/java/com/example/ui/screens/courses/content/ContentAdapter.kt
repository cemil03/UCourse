package com.example.ui.screens.courses.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.ui.databinding.LayoutItemCourseBinding


class ContentAdapter(
    private val onItemClick: (ContentItem) -> Unit,
    private val onFavoriteClick: (ContentItem) -> Unit
) : ListAdapter<ContentItem, ContentViewHolder>(CourseDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val binding = LayoutItemCourseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentViewHolder(binding, onItemClick, onFavoriteClick)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
