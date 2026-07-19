package com.example.itcoursetestapp.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.itcoursetestapp.domain.auth.session.AuthSessionRepository
import com.example.itcoursetestapp.domain.auth.session.EmailEncoder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthSessionRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val emailEncoder: EmailEncoder
) : AuthSessionRepository {

    override suspend fun saveUserSession(email: String) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = true
            preferences[USER_EMAIL_KEY] = emailEncoder.encode(email)
        }
    }

    override fun isUserLoggedIn(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }
    }

    companion object {
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }
}