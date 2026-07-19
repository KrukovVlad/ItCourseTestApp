package com.example.itcoursetestapp.domain.auth

import com.example.itcoursetestapp.domain.auth.exceptions.ValidationException

class ValidateEmailUseCase {
    operator fun invoke(email: String) {
        if (!email.matches(Regex(EMAIL_REGEX))) {
            throw ValidationException.InvalidEmailException()
        }
    }

    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
    }
}