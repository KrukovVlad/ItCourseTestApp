package com.example.itcoursetestapp.domain.home

import kotlinx.coroutines.flow.Flow

class FetchCoursesUseCase(
    private val courseRepository: CourseRepository
) {
    operator fun invoke(): Flow<List<Course>> {
        return courseRepository.fetchCoursesFlow()
    }
}