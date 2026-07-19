package com.example.itcoursetestapp.domain.auth

import com.example.itcoursetestapp.domain.auth.exceptions.ValidationException

class ValidatePasswordUseCase {
    operator fun invoke(password: String) {
        if (password.isEmpty()) {
            throw ValidationException.EmptyPasswordException()
        }
        if (password.length < PASSWORD_MIN_LENGTH) {
            throw ValidationException.PasswordTooShortException()
        }
    }

    companion object {
        private const val PASSWORD_MIN_LENGTH = 8
    }
}