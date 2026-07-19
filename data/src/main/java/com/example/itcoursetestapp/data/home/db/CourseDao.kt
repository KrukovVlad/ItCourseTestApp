package com.example.itcoursetestapp.data.home.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses")
    fun fetchCoursesFlow(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses")
    suspend fun fetchCoursesSnapshot(): List<CourseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)

    @Update
    suspend fun updateCourse(course: CourseEntity)

    @Query("UPDATE courses SET hasLike = NOT hasLike WHERE id = :id")
    suspend fun toggleLike(id: Int)
}