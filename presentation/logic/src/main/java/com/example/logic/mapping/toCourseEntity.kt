package com.example.logic.mapping

import com.example.data.local.course.CourseEntity
import com.example.ui.screens.courses.content.ContentItem

fun ContentItem.toCourseEntity() = CourseEntity(
    id = id,
    title = title,
    caption = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = true,
    publishDate = publishDate,
)
