package com.example.itcoursetestapp.data.home

import com.example.itcoursetestapp.data.home.api.CourseApi
import com.example.itcoursetestapp.data.home.db.CourseDao
import com.example.itcoursetestapp.data.home.db.CourseEntity
import com.example.itcoursetestapp.domain.home.Course
import com.example.itcoursetestapp.domain.home.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CourseRepositoryImpl(
    private val api: CourseApi,
    private val dao: CourseDao
) : CourseRepository {

    override fun fetchCoursesFlow(): Flow<List<Course>> {
        return dao.fetchCoursesFlow().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncCourses(): Result<Unit> {
        return try {
            val response = api.fetchCourses(FILE_ID, EXPORT_FORMAT)
            val remoteCourses = response.courses.map { dto ->
                CourseEntity(
                    id = dto.id,
                    title = dto.title,
                    text = dto.text,
                    price = dto.price,
                    rate = dto.rate,
                    startDate = dto.startDate,
                    hasLike = dto.hasLike,
                    publishDate = dto.publishDate
                )
            }

            val localCourses = dao.fetchCoursesSnapshot()
            val localCoursesMap = localCourses.associateBy { it.id }

            remoteCourses.forEach { remoteCourse ->
                val localCourse = localCoursesMap[remoteCourse.id]
                if (localCourse == null) {
                    dao.insertCourse(remoteCourse)
                } else {
                    if (!remoteCourse.isDataEqual(localCourse)) {
                        val updatedCourse = remoteCourse.copy(hasLike = localCourse.hasLike)
                        dao.updateCourse(updatedCourse)
                    }
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun toggleLike(courseId: Int) {
        dao.toggleLike(courseId)
    }

    companion object {
        private const val FILE_ID = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"
        private const val EXPORT_FORMAT = "download"
    }
}