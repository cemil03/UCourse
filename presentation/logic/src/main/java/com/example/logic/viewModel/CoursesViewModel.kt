package com.example.logic.viewModel

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.CoursesRepository
import com.example.logic.mapping.toContentItem
import com.example.logic.mapping.toCourseEntity
import com.example.ui.screens.courses.CoursesViewModelApi
import com.example.ui.screens.courses.content.ContentItem
import com.example.ui.utils.launchWithLoading
import com.example.ui.utils.noReply
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val coursesRepository: CoursesRepository,
) : CoursesViewModelApi() {

    private var courses: List<ContentItem>? = null

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isLoading: Flow<Boolean> = _isLoading

    private val _error: MutableSharedFlow<String> =
        MutableSharedFlow(replay = 0, extraBufferCapacity = 1)
    override val error: Flow<String> = _error

    private val _items: MutableSharedFlow<List<ContentItem>> = noReply()
    override val items: Flow<List<ContentItem>> = _items

    override fun onFavoriteClick(item: ContentItem) {
        viewModelScope.launchWithLoading(
            loading = _isLoading,
            exception = ::handleException
        ) {
            if (item.hasLike) {
                coursesRepository.deleteCourse(item.id)
            } else {
                coursesRepository.saveCourse(item.toCourseEntity())
            }

            selectItem(item)?.let {
                courses = it
                _items.emit(it)
            }
        }
    }

    override fun onDateFilterClick() {
        viewModelScope.launch {
            val sortedItems = courses?.sortedByDescending { it.publishDate }
            _items.emit(checkNotNull(sortedItems))
        }
    }


    override fun getCourses() {
        viewModelScope.launchWithLoading(
            loading = _isLoading,
            exception = ::handleException
        ) {
            val items = coursesRepository.getCourses().toContentItem()
            val favoritesCourses = coursesRepository.getLocalCourses().map { it.toContentItem() }
            val contents = items.map { contentItem ->
                contentItem.copy(hasLike = favoritesCourses.any { it.id == contentItem.id })
            }
            courses = contents
            _items.emit(contents)
        }
    }

    private fun selectItem(item: ContentItem) = courses?.map {
        if (it.id == item.id) it.copy(hasLike = !it.hasLike) else it
    }

    private fun handleException(throwable: Throwable) {
        _error.tryEmit(throwable.toString())
    }
}
