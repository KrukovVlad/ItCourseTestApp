package com.example.itcoursetestapp.presentation.auth

import com.example.itcoursetestapp.presentation.databinding.FragmentLoginBinding

sealed interface AuthState {
    val email: String
    fun apply(binding: FragmentLoginBinding)

    data class Default(override val email: String = "") : AuthState {
        override fun apply(binding: FragmentLoginBinding) {
            binding.AuthButtonBtn.isEnabled = false
            binding.AuthButtonBtn.alpha = 0.5f
        }
    }

    data class Valid(override val email: String) : AuthState {
        override fun apply(binding: FragmentLoginBinding) {
            binding.AuthButtonBtn.isEnabled = true
            binding.AuthButtonBtn.alpha = 1.0f
        }
    }
}