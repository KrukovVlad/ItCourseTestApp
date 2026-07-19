package com.example.itcoursetestapp.presentation.home.adapter

import com.example.itcoursetestapp.domain.home.Course

sealed interface HomeListItem {
    data class CourseItem(val course: Course) : HomeListItem
    data object SkeletonItem : HomeListItem
}