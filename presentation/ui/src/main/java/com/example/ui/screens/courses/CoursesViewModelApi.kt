package com.example.ui.screens.courses

import androidx.lifecycle.ViewModel
import com.example.ui.screens.courses.content.ContentItem
import kotlinx.coroutines.flow.Flow

abstract class CoursesViewModelApi : ViewModel() {

    abstract val items: Flow<List<ContentItem>>
    abstract val isLoading: Flow<Boolean>
    abstract val error: Flow<String>

    abstract fun getCourses()
    abstract fun onFavoriteClick(item: ContentItem)
    abstract fun onDateFilterClick()
}
