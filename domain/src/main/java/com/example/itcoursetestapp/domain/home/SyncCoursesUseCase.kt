package com.example.itcoursetestapp.domain.home

class SyncCoursesUseCase(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return courseRepository.syncCourses()
    }
}