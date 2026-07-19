package com.example.itcoursetestapp.domain.socials

interface SocialsRepository {
    suspend fun fetchVkUrl(): String
    suspend fun fetchOkUrl(): String
}