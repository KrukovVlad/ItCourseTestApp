package com.example.itcoursetestapp.domain.auth

interface SocialsRepository {
    suspend fun fetchVkUrl(): String
    suspend fun fetchOkUrl(): String
}