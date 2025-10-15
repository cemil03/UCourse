package com.example.logic.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.CoursesRepository
import com.example.logic.mapping.toContentItem
import com.example.ui.screens.courses.CoursesViewModelApi
import com.example.ui.screens.courses.content.ContentItem
import com.example.ui.utils.noReply
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val coursesRepository: CoursesRepository
) : CoursesViewModelApi() {

    private val _items: MutableSharedFlow<List<ContentItem>> = noReply()
    override val items: Flow<List<ContentItem>> = _items

    init {
        getCourses()
    }

    override fun onItemClick(item: ContentItem) {
    }


    private fun getCourses() {
        viewModelScope.launch {
            val courses = coursesRepository.getCourses().toContentItem()
            _items.emit(courses)
        }
    }
}
