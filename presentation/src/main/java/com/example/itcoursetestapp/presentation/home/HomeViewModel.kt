package com.example.itcoursetestapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itcoursetestapp.domain.home.Course
import com.example.itcoursetestapp.domain.home.FetchCoursesUseCase
import com.example.itcoursetestapp.domain.home.SyncCoursesUseCase
import com.example.itcoursetestapp.domain.home.ToggleLikeUseCase
import com.example.itcoursetestapp.domain.core.DispatcherProvider
import com.example.itcoursetestapp.presentation.home.adapter.HomeListItem.CourseItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchCoursesUseCase: FetchCoursesUseCase,
    private val syncCoursesUseCase: SyncCoursesUseCase,
    private val toggleLikeUseCase: ToggleLikeUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _actions = MutableSharedFlow<HomeAction>()
    val actions = _actions.asSharedFlow()

    private var currentCourses: List<Course> = emptyList()
    private var isSortedByDate = false

    init {
        viewModelScope.launch(dispatchers.io) {
            fetchCoursesUseCase().collect { courses ->
                currentCourses = courses
                updateState()
            }
        }
        refresh()
    }

    private fun updateState() {
        if (currentCourses.isEmpty()) {
            _state.value = HomeState.Loading
            return
        }

        val sortedCourses = if (isSortedByDate) {
            currentCourses.sortedByDescending { it.publishDate }
        } else {
            currentCourses
        }

        val items = sortedCourses.map { CourseItem(it) }
        _state.value = HomeState.Success(items)
    }

    private fun refresh() {
        viewModelScope.launch(dispatchers.io) {
            val result = syncCoursesUseCase()
            result.exceptionOrNull()?.let {
                if (currentCourses.isEmpty()) {
                    _state.value = HomeState.Error(it.message ?: "Unknown error")
                }
            }
        }
    }

    fun toggleSort() {
        isSortedByDate = !isSortedByDate
        updateState()
        viewModelScope.launch(dispatchers.main) {
            _actions.emit(HomeAction.ScrollToTop)
        }
    }
    
    fun toggleLike(courseId: Int) {
        viewModelScope.launch(dispatchers.io) {
            toggleLikeUseCase(courseId)
        }
    }
}