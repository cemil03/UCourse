package com.example.ucourse.di.modules

import android.os.Bundle
import com.example.logic.viewModel.AuthViewModel
import com.example.logic.viewModel.CourseDetailsViewModel
import com.example.logic.viewModel.CoursesViewModel
import com.example.logic.viewModel.FavoritesViewModel
import com.example.ui.activity.auth.AuthViewModelApi
import com.example.ui.screens.courseDetails.CourseDetailsViewModelApi
import com.example.ui.screens.courseDetails.CourseDetailsViewModelApi.Companion.COURSE_KEY
import com.example.ui.screens.courses.CoursesViewModelApi
import com.example.ui.screens.courses.content.ContentItem
import com.example.ui.screens.favorites.FavoritesViewModelApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<AuthViewModelApi> {
        AuthViewModel()
    }

    viewModel<CoursesViewModelApi> {
        CoursesViewModel(coursesRepository = get())
    }

    viewModel<FavoritesViewModelApi> {
        FavoritesViewModel(coursesRepository = get(), coursesDao = get())
    }

    viewModel<CourseDetailsViewModelApi> { (args: Bundle) ->
        CourseDetailsViewModel(
            contentItem = requireNotNull(
                args.getParcelable(
                    COURSE_KEY,
                    ContentItem::class.java
                )
            ), coursesRepository = get()
        )
    }
}
