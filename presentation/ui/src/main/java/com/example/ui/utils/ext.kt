package com.example.ui.utils

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateMargins

fun View.addInsetMarginTop(initialMarginTop: Int) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val initialMarginTopPx = (initialMarginTop * resources.displayMetrics.density).toInt()
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        (view.layoutParams as ViewGroup.MarginLayoutParams).updateMargins(
            top = initialMarginTopPx + insets.top
        )
        windowInsets
    }
}
