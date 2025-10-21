package com.example.ui.screens.courses.content

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContentItem(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
): Parcelable
