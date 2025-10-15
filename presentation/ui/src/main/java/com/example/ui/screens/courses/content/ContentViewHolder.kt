package com.example.ui.screens.courses.content

import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.LayoutItemCourseBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ContentViewHolder(
    private val binding: LayoutItemCourseBinding,
    private val onItemClick: (ContentItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contentItem: ContentItem) = with(binding) {
        initBlurItems()
        courseTitle.text = contentItem.title
        courseCaption.text = contentItem.text
        coursePrice.text = contentItem.price
        starsPoint.text = contentItem.rate
        publishDate.text = formatDate(contentItem.startDate)

        root.setOnClickListener { onItemClick(contentItem) }
    }

    private fun initBlurItems() = with(binding) {
        blurRating.clipToOutline = true
        blurDate.clipToOutline = true
        blurRating.setupWith(blurTarget).setBlurRadius(5f)
        blurDate.setupWith(blurTarget).setBlurRadius(5f)
    }

    private fun formatDate(date: String): String =
        LocalDate.parse(date)
            .format(DateTimeFormatter.ofPattern(DATE_PATTERN, Locale(LOCALE_RUSSIAN)))


    companion object {
        private const val LOCALE_RUSSIAN = "ru"
        private const val DATE_PATTERN = "d MMMM yyyy"
    }
}