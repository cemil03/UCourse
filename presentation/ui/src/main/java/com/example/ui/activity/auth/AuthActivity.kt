package com.example.ui.activity.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui.R
import com.example.ui.activity.main.MainActivity
import com.example.ui.databinding.ActivityAuthBinding
import com.example.ui.utils.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val WK_SOCIAL_URL = "https://vk.com/"
private const val OK_SOCIAL_URL = "https://ok.ru/"

class AuthActivity : AppCompatActivity(R.layout.activity_auth) {

    private val binding by viewBinding(ActivityAuthBinding::bind)
    private val viewModel by viewModel<AuthViewModelApi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModelInputs()
        bindViewModelOutputs()
    }

    private fun bindViewModelInputs() = with(binding) {
        actionLogin.setOnClickListener {
            startActivity(
                Intent(
                    this@AuthActivity,
                    MainActivity::class.java
                )
            )
        }
        editEmail.addTextChangedListener {
            viewModel.checkValidationEmail(it.toString())
        }

        editPassword.addTextChangedListener {
            viewModel.checkValidationPassword(it.toString())
        }

        actionWkSocial.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(WK_SOCIAL_URL)))
        }

        actionOkSocial.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(OK_SOCIAL_URL)))
        }
    }

    private fun bindViewModelOutputs() = with(viewModel) {
        isLoginEnable.bind(this@AuthActivity) {
            binding.actionLogin.isEnabled = it
        }
    }
}
