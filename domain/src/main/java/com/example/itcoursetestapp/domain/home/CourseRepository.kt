package com.example.itcoursetestapp.domain.home

import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun fetchCoursesFlow(): Flow<List<Course>>
    suspend fun syncCourses(): Result<Unit>
    suspend fun toggleLike(courseId: Int)
}