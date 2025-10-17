package com.example.ui.screens.courses.content

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import com.example.ui.databinding.LayoutItemCourseBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ContentViewHolder(
    private val binding: LayoutItemCourseBinding,
    private val onItemClick: (ContentItem) -> Unit,
    private val onFavoriteClick: (ContentItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contentItem: ContentItem) = with(binding) {
        initBlurItems()
        initFavoriteView(root.context, contentItem.hasLike)
        courseTitle.text = contentItem.title
        courseCaption.text = contentItem.text
        coursePrice.text = contentItem.price
        starsPoint.text = contentItem.rate
        publishDate.text = formatDate(contentItem.startDate)

        root.setOnClickListener { onItemClick(contentItem) }
        actionFavorite.setOnClickListener { onFavoriteClick(contentItem) }
    }

    private fun initBlurItems() = with(binding) {
        blurRating.clipToOutline = true
        blurDate.clipToOutline = true
        blurRating.setupWith(blurTarget).setBlurRadius(BLUR_RADIUS)
        blurDate.setupWith(blurTarget).setBlurRadius(BLUR_RADIUS)
    }

    private fun formatDate(date: String): String =
        LocalDate.parse(date)
            .format(DateTimeFormatter.ofPattern(DATE_PATTERN, Locale(LOCALE_RUSSIAN)))


    private fun initFavoriteView(context: Context, isFavorite: Boolean) = with(binding) {
        val icon = if (isFavorite) {
            ContextCompat.getDrawable(context, R.drawable.ic_saved)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_favorite_small)
        }
        actionFavorite.setImageDrawable(icon)
    }


    companion object {
        private const val BLUR_RADIUS = 5f
        private const val LOCALE_RUSSIAN = "ru"
        private const val DATE_PATTERN = "d MMMM yyyy"
    }
}
