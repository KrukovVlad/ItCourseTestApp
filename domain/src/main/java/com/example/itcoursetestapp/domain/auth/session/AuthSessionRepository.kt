package com.example.itcoursetestapp.domain.auth.session

import kotlinx.coroutines.flow.Flow

interface AuthSessionRepository {
    suspend fun saveUserSession(email: String)
    fun isUserLoggedIn(): Flow<Boolean>
}