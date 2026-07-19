package com.example.itcoursetestapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itcoursetestapp.domain.auth.BrowserOpener
import com.example.itcoursetestapp.domain.auth.FetchOkUrlUseCase
import com.example.itcoursetestapp.domain.auth.FetchVkUrlUseCase
import com.example.itcoursetestapp.domain.auth.ValidateEmailUseCase
import com.example.itcoursetestapp.domain.auth.ValidatePasswordUseCase
import com.example.itcoursetestapp.domain.auth.exceptions.ValidationException
import com.example.itcoursetestapp.domain.core.DispatcherProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val fetchVkUrlUseCase: FetchVkUrlUseCase,
    private val fetchOkUrlUseCase: FetchOkUrlUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val browserOpener: BrowserOpener,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Default())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<AuthEvent>()
    val events: SharedFlow<AuthEvent> = _events.asSharedFlow()

    private var currentPassword = ""

    fun onEmailChanged(email: String) {
        validateInput(email, currentPassword)
    }

    fun onPasswordChanged(password: String) {
        currentPassword = password
        validateInput(_state.value.email, currentPassword)
    }

    private fun validateInput(email: String, password: String) {
        try {
            validateEmailUseCase(email)
            validatePasswordUseCase(password)
            _state.value = AuthState.Valid(email)
        } catch (e: ValidationException) {
            _state.value = AuthState.Default(email)
        }
    }

    fun onLoginClicked() {
        if (_state.value is AuthState.Valid) {
            viewModelScope.launch(dispatchers.main) {
                _events.emit(AuthEvent.NavigateToMain)
            }
        }
    }

    fun onVkClicked() {
        viewModelScope.launch(dispatchers.io) {
            val url = fetchVkUrlUseCase()
            browserOpener.openUrl(url)
        }
    }

    fun onOkClicked() {
        viewModelScope.launch(dispatchers.io) {
            val url = fetchOkUrlUseCase()
            browserOpener.openUrl(url)
        }
    }
}