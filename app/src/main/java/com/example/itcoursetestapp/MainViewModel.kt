package com.example.itcoursetestapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itcoursetestapp.domain.auth.session.CheckUserSessionUseCase
import com.example.itcoursetestapp.domain.auth.session.exceptions.SessionNotFoundException
import com.example.itcoursetestapp.domain.core.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val checkUserSessionUseCase: CheckUserSessionUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow<Int?>(null)
    val startDestination: StateFlow<Int?> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch(dispatchers.io) {
            try {
                checkUserSessionUseCase()
                _startDestination.value = com.example.itcoursetestapp.presentation.R.id.mainContainerFragment
            } catch (e: SessionNotFoundException) {
                _startDestination.value = com.example.itcoursetestapp.presentation.R.id.authFragment
            } finally {
                _isLoading.value = false
            }
        }
    }
}