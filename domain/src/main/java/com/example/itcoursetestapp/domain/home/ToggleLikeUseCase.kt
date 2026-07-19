package com.example.itcoursetestapp.domain.home

class ToggleLikeUseCase(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int) {
        courseRepository.toggleLike(courseId)
    }
}