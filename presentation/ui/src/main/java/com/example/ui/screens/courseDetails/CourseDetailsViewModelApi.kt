package com.example.ui.screens.courseDetails

import androidx.lifecycle.ViewModel
import com.example.ui.screens.courses.content.ContentItem
import kotlinx.coroutines.flow.Flow

abstract class CourseDetailsViewModelApi : ViewModel() {

    abstract val isFavorite: Flow<Boolean>
    abstract val contentItem: ContentItem

    abstract fun onFavoriteClick()

    companion object {
        const val COURSE_KEY = "COURSE_KEY"
    }
}
