package com.example.ucourse.di.modules

import com.example.logic.AuthViewModel
import com.example.ui.activity.auth.AuthViewModelApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<AuthViewModelApi> {
        AuthViewModel()
    }
}
