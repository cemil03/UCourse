package com.example.ui.screens.courseDetails

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui.R
import com.example.ui.databinding.ScreenCourseDetailsBinding
import com.example.ui.screens.courses.content.ContentItem
import com.example.ui.utils.addInsetMarginTop
import com.example.ui.utils.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class CourseDetailsScreen : Fragment(R.layout.screen_course_details) {

    private val binding by viewBinding(ScreenCourseDetailsBinding::bind)
    private val viewModel: CourseDetailsViewModelApi by viewModel { parametersOf(arguments) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actionBack.addInsetMarginTop(BACK_BUTTON_MARGIN_TOP)
        renderView(viewModel.contentItem)
        bindViewModelOutputs()
        bindViewModelInputs()
    }

    private fun bindViewModelOutputs() = with(viewModel) {
        isFavorite.bind(viewLifecycleOwner) {
            binding.actionFavorite.setImageDrawable(getFavoriteIcon(it))
        }
    }

    private fun bindViewModelInputs() = with(binding) {
        actionBack.setOnClickListener {
            findNavController().popBackStack()
        }

        actionFavorite.setOnClickListener {
            viewModel.onFavoriteClick()
        }
    }


    private fun renderView(contentItem: ContentItem) = with(binding) {
        initBlurItems()
        courseTitle.text = contentItem.title
        starsPoint.text = contentItem.rate
        publishDate.text = formatDate(contentItem.startDate)
        courseCaptions.text = contentItem.text
    }

    private fun initBlurItems() = with(binding) {
        blurRating.clipToOutline = true
        blurDate.clipToOutline = true
        blurRating.setupWith(blurTarget).setBlurRadius(BLUR_RADIUS)
        blurDate.setupWith(blurTarget).setBlurRadius(BLUR_RADIUS)
    }

    private fun getFavoriteIcon(isFavorite: Boolean) = if (isFavorite) {
        ContextCompat.getDrawable(requireContext(), R.drawable.ic_saved)
    } else {
        ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_black)
    }

    private fun formatDate(date: String): String =
        LocalDate.parse(date)
            .format(DateTimeFormatter.ofPattern(DATE_PATTERN, Locale(LOCALE_RUSSIAN)))


    companion object {
        private const val BLUR_RADIUS = 5f
        private const val LOCALE_RUSSIAN = "ru"
        private const val BACK_BUTTON_MARGIN_TOP = 20
        private const val DATE_PATTERN = "d MMMM yyyy"
    }
}
