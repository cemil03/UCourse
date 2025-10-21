package com.example.ui.screens.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui.R
import com.example.ui.databinding.ScreenProfileBinding
import com.example.ui.utils.addInsetMarginTop

class ProfileScreen : Fragment(R.layout.screen_profile) {

    private val binding by viewBinding(ScreenProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.blurView.setupWith(binding.blurTarget).setBlurRadius(BLUR_RADIUS)
        binding.appName.addInsetMarginTop(APP_NAME_MARGIN_TOP)
    }

    companion object {
        private const val BLUR_RADIUS = 5f
        private const val APP_NAME_MARGIN_TOP = 20
    }
}
