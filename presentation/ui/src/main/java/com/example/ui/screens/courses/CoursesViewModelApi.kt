package com.example.ui.screens.courses

import androidx.lifecycle.ViewModel
import com.example.ui.screens.courses.content.ContentItem
import kotlinx.coroutines.flow.Flow

abstract class CoursesViewModelApi : ViewModel() {

    abstract val items: Flow<List<ContentItem>>

    abstract fun onItemClick(item: ContentItem)
}