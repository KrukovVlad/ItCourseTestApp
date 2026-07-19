package com.example.itcoursetestapp.presentation.home

sealed interface HomeAction {
    fun apply(fragment: HomeFragment)

    data object ScrollToTop : HomeAction {
        override fun apply(fragment: HomeFragment) {
            fragment.binding.CoursesRv.postDelayed({
                fragment.binding.CoursesRv.smoothScrollToPosition(0)
            }, ANIMATION_DELAY)
        }
    }

    companion object {
        private const val ANIMATION_DELAY = 200L
    }
}