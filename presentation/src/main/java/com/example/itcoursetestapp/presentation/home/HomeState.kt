package com.example.itcoursetestapp.presentation.home

import com.example.itcoursetestapp.presentation.home.adapter.HomeListItem

import com.example.itcoursetestapp.presentation.home.adapter.HomeListItem.SkeletonItem

sealed interface HomeState {
    fun apply(fragment: HomeFragment)

    data object Loading : HomeState {
        override fun apply(fragment: HomeFragment) {
            fragment.adapter.items = List(5) { SkeletonItem }
        }
    }

    data class Success(val items: List<HomeListItem>) : HomeState {
        override fun apply(fragment: HomeFragment) {
            fragment.adapter.items = items
        }
    }

    data class Error(val message: String) : HomeState {
        override fun apply(fragment: HomeFragment) = Unit
    }
}