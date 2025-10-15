package com.example.data.remote.course

import com.example.data.remote.course.contract.CourseResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val COURSES_ID = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"
private const val EXPORT_MODE = "download"

interface CoursesApi {

    @GET("u/0/uc")
    suspend fun getCourses(
        @Query("id") id: String = COURSES_ID,
        @Query("export") export: String = EXPORT_MODE
    ): CourseResponse
}