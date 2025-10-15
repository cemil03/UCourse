package com.example.data.remote.course.contract

data class CourseResponse(
    val courses: List<CourseItem>
)

data class CourseItem(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
)
