package com.example.ui.screens.favorites

import androidx.lifecycle.ViewModel
import com.example.ui.screens.courses.content.ContentItem
import kotlinx.coroutines.flow.Flow

abstract class FavoritesViewModelApi : ViewModel() {
    abstract val items: Flow<List<ContentItem>>

    abstract fun getFavoriteCourses()
    abstract fun onFavoriteClick(item: ContentItem)
}
