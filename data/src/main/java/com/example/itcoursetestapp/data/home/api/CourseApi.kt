package com.example.itcoursetestapp.data.home.api

import com.example.itcoursetestapp.data.home.api.dto.CourseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseApi {
    @GET("u/0/uc")
    suspend fun fetchCourses(
        @Query("id") id: String,
        @Query("export") export: String
    ): CourseResponse
}