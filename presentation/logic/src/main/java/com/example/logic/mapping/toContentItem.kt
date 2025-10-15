package com.example.logic.mapping

import com.example.data.remote.course.contract.CourseResponse
import com.example.ui.screens.courses.content.ContentItem

fun CourseResponse.toContentItem() = courses.map {
    ContentItem(
        id = it.id,
        title = it.title,
        text = it.text,
        price = it.price,
        rate = it.rate,
        startDate = it.startDate,
        hasLike = it.hasLike,
        publishDate = it.publishDate,
    )
}
