package com.example.logic

import android.util.Patterns
import com.example.ui.activity.auth.AuthViewModelApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class AuthViewModel : AuthViewModelApi() {

    private val _isValidEmail: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isValidPassword: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override val isLoginEnable: Flow<Boolean> = combine(
        _isValidPassword, _isValidEmail
    ) { isValidPassword, isValidEmail ->
        isValidPassword && isValidEmail
    }

    override fun checkValidationEmail(email: String) {
        _isValidEmail.value = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkValidationPassword(password: String) {
        _isValidPassword.value = password.isNotEmpty()
    }
}
