package com.example.ui.screens.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui.R
import com.example.ui.databinding.ScreenFavoritesBinding
import com.example.ui.screens.courseDetails.CourseDetailsViewModelApi.Companion.COURSE_KEY
import com.example.ui.screens.courses.content.ContentAdapter
import com.example.ui.screens.courses.content.ContentItem
import com.example.ui.utils.addInsetMarginTop
import com.example.ui.utils.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesScreen : Fragment(R.layout.screen_favorites) {

    private val binding by viewBinding(ScreenFavoritesBinding::bind)
    private val viewModel by viewModel<FavoritesViewModelApi>()
    private var coursesAdapter: ContentAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coursesAdapter =
            ContentAdapter(::navigateToDetailsScreen, viewModel::onFavoriteClick)
        binding.recycleViewCourses.adapter = coursesAdapter
        binding.favoriteTitle.addInsetMarginTop(FAVORITE_TEXT_MARGIN_TOP)
        viewModelOutputs()
        viewModel.getFavoriteCourses()
    }

    private fun viewModelOutputs() = with(viewModel) {
        items.bind(viewLifecycleOwner) {
            coursesAdapter?.submitList(it)
        }
    }

    private fun navigateToDetailsScreen(item: ContentItem) {
        val bundle = Bundle().also { it.putParcelable(COURSE_KEY, item) }
        findNavController().navigate(R.id.action_favorite_to_courseDetailsScreen, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        coursesAdapter = null
    }

    companion object {
        private const val FAVORITE_TEXT_MARGIN_TOP = 12
    }
}
