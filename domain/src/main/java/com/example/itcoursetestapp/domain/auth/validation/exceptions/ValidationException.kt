package com.example.itcoursetestapp.domain.auth.validation.exceptions

import com.example.itcoursetestapp.domain.core.exceptions.DomainException

sealed class ValidationException(message: String) : DomainException(message) {
    class InvalidEmailException : ValidationException("Email format is invalid")
    class EmptyPasswordException : ValidationException("Password cannot be empty")
    class PasswordTooShortException : ValidationException("Password must be at least 8 characters")
}