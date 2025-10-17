package com.example.ui.screens.courses

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui.R
import com.example.ui.databinding.ScreenCoursesBinding
import com.example.ui.screens.courses.content.ContentAdapter
import com.example.ui.screens.courses.content.ContentItem
import com.example.ui.utils.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoursesScreen : Fragment(R.layout.screen_courses) {

    private val binding by viewBinding(ScreenCoursesBinding::bind)
    private val viewModel by viewModel<CoursesViewModelApi>()
    private var coursesAdapter: ContentAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coursesAdapter = ContentAdapter(::navigateToDetailsScreen, viewModel::onFavoriteClick)
        binding.recycleViewCourses.adapter = coursesAdapter
        viewModel.getCourses()
        bindViewModelOutputs()
        bindViewModelInputs()
    }


    private fun bindViewModelOutputs() = with(viewModel) {
        items.bind(viewLifecycleOwner) {
            coursesAdapter?.submitList(it)
        }

        isLoading.bind(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
            binding.recycleViewCourses.isVisible = !it
        }

        error.bind(viewLifecycleOwner, ::showToast)
    }

    private fun bindViewModelInputs() = with(binding) {
        actionDateFilter.setOnClickListener {
            viewModel.onDateFilterClick()
        }
    }

    private fun navigateToDetailsScreen(item: ContentItem) {
        //TODO navigate to details screen
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        coursesAdapter = null
    }
}
