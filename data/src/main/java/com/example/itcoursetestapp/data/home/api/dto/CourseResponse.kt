package com.example.itcoursetestapp.data.home.api.dto

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("courses") val courses: List<CourseDto>
)