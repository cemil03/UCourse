package com.example.domain.repository

import com.example.data.remote.course.CoursesApi

class CoursesRepository(private val api: CoursesApi) {

    suspend fun getCourses() = api.getCourses()
}
