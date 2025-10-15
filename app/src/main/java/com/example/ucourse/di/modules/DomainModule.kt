package com.example.ucourse.di.modules

import com.example.domain.repository.CoursesRepository
import org.koin.dsl.module

val domainModule = module {
    factory {
        CoursesRepository(api = get())
    }
}