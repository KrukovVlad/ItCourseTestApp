package com.example.itcoursetestapp.presentation.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainContainerViewModel : ViewModel() {

    private val _selectedTab = MutableStateFlow<MainContainerState>(MainContainerState.Home)
    val selectedTab: StateFlow<MainContainerState> = _selectedTab.asStateFlow()

    fun selectTab(tab: MainContainerState) {
        if (_selectedTab.value != tab) {
            _selectedTab.value = tab
        }
    }
}