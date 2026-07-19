package com.example.itcoursetestapp.presentation.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.itcoursetestapp.presentation.R
import com.example.itcoursetestapp.presentation.databinding.FragmentMainContainerBinding
import com.example.itcoursetestapp.presentation.favorites.FavoritesFragment
import com.example.itcoursetestapp.presentation.home.HomeFragment

sealed class MainContainerState {
    abstract fun apply(binding: FragmentMainContainerBinding, childFragmentManager: FragmentManager)

    protected fun applyContainerState(
        binding: FragmentMainContainerBinding,
        childFragmentManager: FragmentManager,
        selectedBtn: View,
        fragmentClass: Class<out Fragment>,
        fragmentFactory: () -> Fragment
    ) {
        binding.NavMainBtn.isSelected = binding.NavMainBtn === selectedBtn
        binding.NavFavoritesBtn.isSelected = binding.NavFavoritesBtn === selectedBtn
        binding.NavAccountBtn.isSelected = binding.NavAccountBtn === selectedBtn

        val currentFragment = childFragmentManager.findFragmentById(R.id.MainNavHostContainer_fl)
        if (currentFragment?.javaClass != fragmentClass) {
            childFragmentManager.beginTransaction()
                .replace(R.id.MainNavHostContainer_fl, fragmentFactory())
                .commit()
        }
    }

    data object Home : MainContainerState() {
        override fun apply(binding: FragmentMainContainerBinding, childFragmentManager: FragmentManager) {
            applyContainerState(
                binding,
                childFragmentManager,
                binding.NavMainBtn,
                HomeFragment::class.java)
            { HomeFragment() }
        }
    }

    data object Favorites : MainContainerState() {
        override fun apply(binding: FragmentMainContainerBinding, childFragmentManager: FragmentManager) {
            applyContainerState(
                binding,
                childFragmentManager,
                binding.NavFavoritesBtn,
                FavoritesFragment::class.java)
            { FavoritesFragment() }
        }
    }

    data object Account : MainContainerState() {
        override fun apply(binding: FragmentMainContainerBinding, childFragmentManager: FragmentManager) {
            binding.NavMainBtn.isSelected = false
            binding.NavFavoritesBtn.isSelected = false
            binding.NavAccountBtn.isSelected = true
        }
    }
}