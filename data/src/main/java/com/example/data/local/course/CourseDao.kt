package com.example.data.local.course

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.course.CourseEntity.Companion.COURSE_TABLE_NAME


@Dao
interface CourseDao {

    @Insert
    suspend fun insertCourse(courseEntity: CourseEntity)

    @Query("SELECT * FROM $COURSE_TABLE_NAME")
    suspend fun getAllCourses(): List<CourseEntity>

    @Query("DELETE FROM $COURSE_TABLE_NAME WHERE id=:idCourse")
    suspend fun deleteCourse(idCourse: Int)
}
