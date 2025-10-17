package com.example.domain.repository

import com.example.data.local.course.CourseDao
import com.example.data.local.course.CourseEntity
import com.example.data.remote.course.CoursesApi

class CoursesRepository(private val courseDao: CourseDao, private val api: CoursesApi) {

    suspend fun getCourses() = api.getCourses()

    suspend fun getLocalCourses() = courseDao.getAllCourses()
    suspend fun saveCourse(course: CourseEntity) = courseDao.insertCourse(course)
    suspend fun deleteCourse(courseId: Int) = courseDao.deleteCourse(courseId)
}
