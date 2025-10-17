package com.example.logic.viewModel

import androidx.lifecycle.viewModelScope
import com.example.data.local.course.CourseDao
import com.example.domain.repository.CoursesRepository
import com.example.logic.mapping.toContentItem
import com.example.logic.mapping.toCourseEntity
import com.example.ui.screens.courses.content.ContentItem
import com.example.ui.screens.favorites.FavoritesViewModelApi
import com.example.ui.utils.noReply
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val coursesRepository: CoursesRepository,
    private val coursesDao: CourseDao
) : FavoritesViewModelApi() {

    private var favorites: List<ContentItem>? = null
    private val _items: MutableSharedFlow<List<ContentItem>> = noReply()
    override val items: Flow<List<ContentItem>> = _items


    override fun getFavoriteCourses() {
        viewModelScope.launch {
            val favoriteCourses = coursesDao.getAllCourses().map { it.toContentItem() }
            favorites = favoriteCourses
            _items.emit(favoriteCourses)
        }
    }

    override fun onFavoriteClick(item: ContentItem) {
        viewModelScope.launch {
            if (item.hasLike) {
                coursesRepository.deleteCourse(item.id)
            } else {
                coursesRepository.saveCourse(item.toCourseEntity())
            }

            selectItem(item)?.let {
                favorites = it
                _items.emit(it)
            }
        }
    }

    private fun selectItem(item: ContentItem) = favorites?.map {
        if (it.id == item.id) it.copy(hasLike = !it.hasLike) else it
    }
}
