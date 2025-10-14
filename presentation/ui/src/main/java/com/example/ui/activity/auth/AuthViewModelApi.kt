package com.example.ui.activity.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class AuthViewModelApi : ViewModel() {

    abstract val isLoginEnable: Flow<Boolean>

    abstract fun checkValidationEmail(email: String)
    abstract fun checkValidationPassword(password: String)
}
