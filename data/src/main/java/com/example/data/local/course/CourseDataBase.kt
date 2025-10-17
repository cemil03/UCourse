package com.example.data.local.course

import androidx.room.Database
import androidx.room.RoomDatabase

private const val DATABASE_VERSION = 1

@Database(entities = [CourseEntity::class], version = DATABASE_VERSION)
abstract class CourseDataBase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}
