package com.example.data.local.course

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.local.course.CourseEntity.Companion.COURSE_TABLE_NAME

@Entity(tableName = COURSE_TABLE_NAME)
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "caption") val caption: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "rate") val rate: String,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "hasLike") val hasLike: Boolean,
    @ColumnInfo(name = "publishDate") val publishDate: String,
) {
    companion object {
        const val COURSE_TABLE_NAME = "COURSE"
    }
}
