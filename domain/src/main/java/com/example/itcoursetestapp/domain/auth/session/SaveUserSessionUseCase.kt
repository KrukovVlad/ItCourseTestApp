package com.example.itcoursetestapp.domain.auth.session

class SaveUserSessionUseCase(private val repository: AuthSessionRepository) {
    suspend operator fun invoke(email: String) {
        repository.saveUserSession(email)
    }
}