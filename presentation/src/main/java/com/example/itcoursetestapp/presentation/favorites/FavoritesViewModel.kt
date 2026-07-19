package com.example.itcoursetestapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itcoursetestapp.domain.home.Course
import com.example.itcoursetestapp.domain.home.FetchCoursesUseCase
import com.example.itcoursetestapp.domain.home.SyncCoursesUseCase
import com.example.itcoursetestapp.domain.home.ToggleLikeUseCase
import com.example.itcoursetestapp.domain.core.DispatcherProvider
import com.example.itcoursetestapp.presentation.home.adapter.HomeListItem.CourseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val fetchCoursesUseCase: FetchCoursesUseCase,
    private val syncCoursesUseCase: SyncCoursesUseCase,
    private val toggleLikeUseCase: ToggleLikeUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _state = MutableStateFlow<FavoritesState>(FavoritesState.Loading)
    val state: StateFlow<FavoritesState> = _state.asStateFlow()

    private var currentFavoriteCourses: List<Course> = emptyList()

    init {
        viewModelScope.launch(dispatchers.io) {
            fetchCoursesUseCase().collect { courses ->
                currentFavoriteCourses = courses.filter { it.hasLike }
                updateState()
            }
        }
        refresh()
    }

    private fun updateState() {
        if (currentFavoriteCourses.isEmpty()) {
            _state.value = FavoritesState.Success(emptyList())
            return
        }

        val items = currentFavoriteCourses.map { CourseItem(it) }
        _state.value = FavoritesState.Success(items)
    }

    private fun refresh() {
        viewModelScope.launch(dispatchers.io) {
            val result = syncCoursesUseCase()
            result.exceptionOrNull()?.let {
                if (currentFavoriteCourses.isEmpty()) {
                    _state.value = FavoritesState.Error(it.message ?: "Unknown error")
                }
            }
        }
    }

    fun toggleLike(courseId: Int) {
        viewModelScope.launch(dispatchers.io) {
            toggleLikeUseCase(courseId)
        }
    }
}