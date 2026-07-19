package com.example.itcoursetestapp.domain.auth.session

import com.example.itcoursetestapp.domain.auth.session.exceptions.SessionNotFoundException
import kotlinx.coroutines.flow.first

class CheckUserSessionUseCase(private val repository: AuthSessionRepository) {
    suspend operator fun invoke() {
        val isLoggedIn = repository.isUserLoggedIn().first()
        if (!isLoggedIn) {
            throw SessionNotFoundException()
        }
    }
}