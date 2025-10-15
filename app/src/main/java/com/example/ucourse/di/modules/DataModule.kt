package com.example.ucourse.di.modules

import com.example.data.remote.course.CoursesApi
import com.example.ucourse.di.network.provideApi
import org.koin.dsl.module

val dataModule = module {
    single<CoursesApi> {
        provideApi(retrofit = get())
    }
}