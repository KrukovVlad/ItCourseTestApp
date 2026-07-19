package com.example.itcoursetestapp.presentation.favorites

import com.example.itcoursetestapp.presentation.home.adapter.HomeListItem
import com.example.itcoursetestapp.presentation.home.adapter.HomeListItem.SkeletonItem

sealed interface FavoritesState {
    fun apply(fragment: FavoritesFragment)

    data object Loading : FavoritesState {
        override fun apply(fragment: FavoritesFragment) {
            fragment.adapter.items = List(5) { SkeletonItem }
        }
    }

    data class Success(val items: List<HomeListItem>) : FavoritesState {
        override fun apply(fragment: FavoritesFragment) {
            fragment.adapter.items = items
        }
    }

    data class Error(val message: String) : FavoritesState {
        override fun apply(fragment: FavoritesFragment) = Unit
    }
}