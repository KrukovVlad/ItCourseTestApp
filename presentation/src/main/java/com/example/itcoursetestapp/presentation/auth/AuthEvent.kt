package com.example.itcoursetestapp.presentation.auth

import androidx.navigation.NavController
import com.example.itcoursetestapp.presentation.R

sealed interface AuthEvent {
    fun apply(navController: NavController)

    data object NavigateToMain : AuthEvent {
        override fun apply(navController: NavController) {
            navController.navigate(R.id.action_authFragment_to_mainContainerFragment)
        }
    }
}