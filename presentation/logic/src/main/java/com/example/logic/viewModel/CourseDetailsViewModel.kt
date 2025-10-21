package com.example.logic.viewModel

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.CoursesRepository
import com.example.logic.mapping.toCourseEntity
import com.example.ui.screens.courseDetails.CourseDetailsViewModelApi
import com.example.ui.screens.courses.content.ContentItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseDetailsViewModel(
    override val contentItem: ContentItem,
    private val coursesRepository: CoursesRepository
) : CourseDetailsViewModelApi() {

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(contentItem.hasLike)
    override val isFavorite: Flow<Boolean> = _isFavorite

    override fun onFavoriteClick() {
        viewModelScope.launch {
            if (_isFavorite.value) {
                coursesRepository.deleteCourse(contentItem.id)
            } else {
                coursesRepository.saveCourse(contentItem.toCourseEntity())
            }
            _isFavorite.update { !it }
        }
    }
}
