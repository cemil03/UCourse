package com.example.ucourse.di.modules

import com.example.logic.viewModel.AuthViewModel
import com.example.logic.viewModel.CoursesViewModel
import com.example.ui.activity.auth.AuthViewModelApi
import com.example.ui.screens.courses.CoursesViewModelApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<AuthViewModelApi> {
        AuthViewModel()
    }

    viewModel<CoursesViewModelApi> {
        CoursesViewModel(coursesRepository = get())
    }
}
