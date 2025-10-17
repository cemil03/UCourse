package com.example.ucourse.di.modules

import androidx.room.Room
import com.example.data.local.course.CourseDataBase
import com.example.data.remote.course.CoursesApi
import com.example.ucourse.di.network.provideApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<CoursesApi> {
        provideApi(retrofit = get())
    }
}

val localDataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CourseDataBase::class.java,
            "course_database"
        ).build()
    }

    factory {
        provideMeditationMetricDao(database = get())
    }
}

private fun provideMeditationMetricDao(database: CourseDataBase) =
    database.courseDao()

val dataModule = repositoryModule + localDataModule
